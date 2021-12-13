package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.hibernate.entites.Client;
import com.example.web_j2ee_project.panier.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Inscription", urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out= response.getWriter()){
            try{
                String user_username = request.getParameter("user_name");
                String user_email = request.getParameter("user_email");
                String user_password = request.getParameter("user_password");
                String user_address = request.getParameter("user_address");
                String user_name = request.getParameter("user_name");
                String user_surname = request.getParameter("user_surname");

                Client user = new Client(user_username, user_password, user_surname, user_name,
                        user_email, user_address);
                if (user.isComplete()){
                    Session hibernateSession = FactoryProvider.getFactory().openSession();
                    Transaction transaction = hibernateSession.beginTransaction();

                    int savedUserId = (int)hibernateSession.save(user);

                    transaction.commit();
                    hibernateSession.close();
                    out.println("Utilisateur enregistrer avec l'id : "+savedUserId);
                }
                else
                {
                    out.println("Un des champs est vide !");
                    return;
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
