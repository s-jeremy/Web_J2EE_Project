package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.dao.UserDao;
import com.example.web_j2ee_project.hibernate.entites.Client;
import com.example.web_j2ee_project.panier.FactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Connexion", urlPatterns = {"/Connexion"})
public class Connexion extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out= response.getWriter()){
            //Récupération de l'identifiant et du mot de passe
            String username = request.getParameter("user_name");
            String user_password = request.getParameter("user_password");
            //Vérification de l'utilisateur et de son mdp dans la base
            UserDao userDao = new UserDao(FactoryProvider.getFactory());
            Client utilisateur = userDao.getUserByEmailPw(username,user_password);
            HttpSession httpSession = request.getSession();

            if(utilisateur == null){
                //Si l'identification à échoué, alors retour et msg erreur
                httpSession.setAttribute("notification","Invalide, essaye un autre");
                response.sendRedirect("login.jsp");
                return;
            } else{
                //Si User Bloqué, alors notification et retour
                if (utilisateur.getBloquer()==1){
                    httpSession.setAttribute("notification","Cette utilisateur est bloquer !");
                    response.sendRedirect("login.jsp");
                    return;
                }
                out.println("<h1> Welcome " + utilisateur.getUsername() + " </h1>");

                // Identification OK : redirection vers page admin ou user selon role
                httpSession.setAttribute("current-user", utilisateur);
                if(utilisateur.getRole().equals("admin")){
                    response.sendRedirect("admin.jsp");
                }
                else if(utilisateur.getRole().equals("user")){
                    response.sendRedirect("market.jsp");
                }
                else{
                    out.println("Aucun rôle identifié");
                }
            }
        }
    }
}
