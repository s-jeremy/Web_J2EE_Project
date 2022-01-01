package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.dao.UserDao;
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

@WebServlet(name = "Inscription", urlPatterns = {"/Inscription"})
public class Inscription extends HttpServlet

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
                String user_username = request.getParameter("user_name");
                String user_email = request.getParameter("user_email");
                String user_password = request.getParameter("user_password");
                String user_address = request.getParameter("user_address");
                String user_name = request.getParameter("user_name");
                String user_surname = request.getParameter("user_surname");
                String user_role = "user";
                int user_bloquer = 0;

                Client user = new Client(user_username, user_password, user_surname, user_name,
                        user_email, user_address, user_role, user_bloquer);
                if (user.isComplete()){
                    UserDao userDao = new UserDao(FactoryProvider.getFactory());

                    if (!userDao.checkIfUserExist(user_username)){
                        Session hibernateSession = FactoryProvider.getFactory().openSession();
                        Transaction transaction = hibernateSession.beginTransaction();

                        int savedUserId = (int)hibernateSession.save(user);

                        transaction.commit();
                        hibernateSession.close();

                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","Inscription Réussite !");
                        response.sendRedirect("register.jsp");
                        return;
                    }
                    else{
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("notification","L'utilisateur existe déja !");
                        response.sendRedirect("register.jsp");
                        return;
                    }
                }
                else
                {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("notification","L'un des champs est vide !");
                    response.sendRedirect("register.jsp");
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
