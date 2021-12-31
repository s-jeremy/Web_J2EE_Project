package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.hibernate.entites.Categorie;
import com.example.web_j2ee_project.hibernate.entites.Client;
import com.example.web_j2ee_project.panier.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjoutCategorie", urlPatterns = {"/AjoutCategorie"})
public class AjoutCategorie extends HttpServlet

{

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
                String categorie_titre = request.getParameter("categorie_titre");
                String categorie_description = request.getParameter("categorie_description");

                Categorie categorie = new Categorie(categorie_titre, categorie_description);

                if (categorie.isComplete()){
                    Session hibernateSession = FactoryProvider.getFactory().openSession();
                    Transaction transaction = hibernateSession.beginTransaction();

                    int savedCategorieId = (int)hibernateSession.save(categorie);

                    transaction.commit();
                    hibernateSession.close();

                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("notification","Ajout réussit !");
                    return;
                }
                else
                {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("notification","L'un des champs est vide !");
                    return;
                }

            } catch (Exception e){
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("notification","Une erreur est survenu !");
                response.sendRedirect("register.jsp");
                return;
            }
        }
    }
}
