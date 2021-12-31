package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.dao.ArticleDao;
import com.example.web_j2ee_project.dao.CategorieDao;
import com.example.web_j2ee_project.hibernate.entites.Article;
import com.example.web_j2ee_project.hibernate.entites.Categorie;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out= response.getWriter()){
            try{
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
