package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.dao.ArticleDao;
import com.example.web_j2ee_project.dao.CategorieDao;
import com.example.web_j2ee_project.dao.UserDao;
import com.example.web_j2ee_project.hibernate.entites.Article;
import com.example.web_j2ee_project.hibernate.entites.Categorie;
import com.example.web_j2ee_project.hibernate.entites.Client;
import com.example.web_j2ee_project.panier.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
@WebServlet(name = "OperationAdmin", urlPatterns = {"/OperationAdmin"})
public class OperationAdmin extends HttpServlet
{
    CategorieDao categorieDao = new CategorieDao(FactoryProvider.getFactory());
    ArticleDao articleDao = new ArticleDao(FactoryProvider.getFactory());
    UserDao userDao = new UserDao(FactoryProvider.getFactory());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request,response);
    }
    //Servlet gérant toutes les fonctionnalités de l'admin
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out= response.getWriter()){
            try{
                //Récupération de l'opération à réalisé
                String operation = request.getParameter("operation");
                //
                //Partie ajouter une catégorie !
                //
                if (operation.trim().equals("ajoutCategorie")){
                    String categorie_titre = request.getParameter("categorie_titre");
                    String categorie_description = request.getParameter("categorie_description");

                    Categorie categorie = new Categorie(categorie_titre, categorie_description);

                    if (categorie.isComplete()){
                        Session hibernateSession = FactoryProvider.getFactory().openSession();
                        Transaction transaction = hibernateSession.beginTransaction();
                        //Paramètres OK + la catégorie n'existe pas, on la crée
                        if (!categorieDao.checkIfExist(categorie_titre)){
                            int savedCategorieId = (int)hibernateSession.save(categorie);

                            transaction.commit();
                            hibernateSession.close();

                            HttpSession httpSession = request.getSession();
                            httpSession.setAttribute("notification","Catégorie \""+categorie_titre+"\" ajouté ! ");
                            response.sendRedirect("admin.jsp");
                            return;
                        }else {
                            HttpSession httpSession = request.getSession();
                            httpSession.setAttribute("notification","La catégorie existe déja !");
                            response.sendRedirect("admin.jsp");
                            return;
                        }
                    }else{
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","L'un des champs est vide ! !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }
                }
                //
                //Partie Ajouter un produit !
                //
                if (operation.trim().equals("ajoutArticle")){
                    String article_nom = request.getParameter("article_nom");
                    String article_description = request.getParameter("article_description");
                    int article_prix = Integer.valueOf(request.getParameter("article_prix"));
                    int article_quantite = Integer.valueOf(request.getParameter("article_quantite"));
                    int categorie_id = Integer.valueOf(request.getParameter("categorie_id"));


                   Article article = new Article(article_nom, article_description,
                           article_prix, article_quantite, categorie_id);
                    System.out.println(article.toString());
                    if (article.isComplete()){
                        Session hibernateSession = FactoryProvider.getFactory().openSession();
                        Transaction transaction = hibernateSession.beginTransaction();
                        //Si produit n'existe pas, on le crée
                        if (!articleDao.checkIfExist(article_nom)){
                            int savedArticleId = (int)hibernateSession.save(article);

                            transaction.commit();
                            hibernateSession.close();

                            HttpSession httpSession = request.getSession();
                            httpSession.setAttribute("notification","Article \""+article_nom+"\" ajouté ! ");
                            response.sendRedirect("admin.jsp");
                            return;
                        }else {
                            HttpSession httpSession = request.getSession();
                            httpSession.setAttribute("notification","L'article existe déja !");
                            response.sendRedirect("admin.jsp");
                            return;
                        }
                    }else{
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","L'un des champs est vide ! !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }
                }
                //
                //Partie bloquer et débloquer utilisateur !
                //
                if (operation.trim().equals("bloquerUtilisateur")){
                    int utilisateur_id = Integer.valueOf(request.getParameter("utilisateur_id"));

                    Client utilisateur = userDao.getUserById(utilisateur_id);

                    if (utilisateur !=null)
                    {

                        Session hibernateSession = FactoryProvider.getFactory().openSession();
                        Transaction transaction = hibernateSession.beginTransaction();
                        //Si l'utilsateur est bloquer, on le débloque
                        if (utilisateur.getBloquer() == 1)
                        {
                            utilisateur.setBloquer(0);
                            hibernateSession.update(utilisateur);
                            transaction.commit();
                            hibernateSession.close();
                            HttpSession httpSession = request.getSession();
                            httpSession.setAttribute("notification", "L'utilisateur a bien était débloqué !");
                            response.sendRedirect("admin.jsp");
                            return;
                        }
                        //Si l'utilisateur n'est pas bloquer, on le bloque
                        else
                        {
                            if(utilisateur.getId() == 1){
                                HttpSession httpSession = request.getSession();
                                httpSession.setAttribute("notification", "L'admin ne peut pas être bloqué !");
                                response.sendRedirect("admin.jsp");
                                return;
                            }
                            utilisateur.setBloquer(1);
                            hibernateSession.update(utilisateur);
                            transaction.commit();
                            hibernateSession.close();
                            HttpSession httpSession = request.getSession();
                            httpSession.setAttribute("notification", "L'utilisateur a bien était bloqué !");
                            response.sendRedirect("admin.jsp");
                            return;
                        }
                    }else
                    {
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification", "Erreur sélection utilisateur !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }

                }

                //
                //Partie supprimer une catégorie !
                //
                if (operation.trim().equals("supprimerCategorie")){
                    int categorie_id = Integer.valueOf(request.getParameter("categorie_id"));

                    Categorie categorie = categorieDao.getCategorie(categorie_id);

                    Session hibernateSession = FactoryProvider.getFactory().openSession();
                    Transaction transaction = hibernateSession.beginTransaction();
                    //Si on arrive a trouver la catégorie en BDD, on la supprime
                    if (categorie!=null)
                    {
                        hibernateSession.delete(categorie);
                        transaction.commit();
                        hibernateSession.close();
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","La catégorie "+categorie.getTitre()+" a bien était supprimer !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }else{
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","Erreur selection de catégorie !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }
                }
                //
                //Partie supprimer un produit !
                //
                if (operation.trim().equals("supprimerArticle")){
                    int article_id = Integer.valueOf(request.getParameter("article_id"));

                    Article article = articleDao.getArticle(article_id);

                    Session hibernateSession = FactoryProvider.getFactory().openSession();
                    Transaction transaction = hibernateSession.beginTransaction();
                    //Si on arrive a trouver la catégorieun produit en BDD, on le supprime
                    if (article!=null)
                    {
                        hibernateSession.delete(article);
                        transaction.commit();
                        hibernateSession.close();
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","Le produit "+article.getNomProduit()+" a bien était supprimé !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }else{
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","Erreur selection de produit !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }
                }
                //
                //Partie modifier quantité produit !
                //
                if (operation.trim().equals("modifierStockArticle")){
                    int article_id = Integer.valueOf(request.getParameter("article_id"));
                    int quantite = Integer.valueOf(request.getParameter("quantite"));

                    Article article = articleDao.getArticle(article_id);

                    Session hibernateSession = FactoryProvider.getFactory().openSession();
                    Transaction transaction = hibernateSession.beginTransaction();
                    //Article trouvé, on lui modifie sa quantité
                    if (article!=null)
                    {
                        article.setQuantiteProduit(quantite);
                        hibernateSession.update(article);
                        transaction.commit();
                        hibernateSession.close();
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","Le stock du produit "+article.getNomProduit()+" est maintenant de "+article.getQuantiteProduit()+" !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }else{
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","Erreur selection de produit !");
                        response.sendRedirect("admin.jsp");
                        return;
                    }
                }
            } catch (Exception e){
                System.out.println("Erreur : "+e);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("notification","Une erreur est survenu !");
                response.sendRedirect("admin.jsp");
                return;
            }
        }
    }
}
