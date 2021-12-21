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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out= response.getWriter()){
            String username = request.getParameter("username");
            String user_password = request.getParameter("user_password");

            UserDao userDao = new UserDao(FactoryProvider.getFactory());
            Client utilisateur = userDao.getUserByEmailPw(username,user_password);
            //System.out.println(utilisateur);
            HttpSession httpSession = request.getSession();

            if(utilisateur == null){
                httpSession.setAttribute("message","Invalide, essaye un autre");
                response.sendRedirect("login.jsp");
                return;
            } else{
                out.println("<h1> Welcome " + utilisateur.getUsername() + " </h1>");
            }


        }
    }
}
