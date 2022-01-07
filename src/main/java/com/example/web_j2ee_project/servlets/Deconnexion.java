package com.example.web_j2ee_project.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Deconnexion", urlPatterns = {"/Deconnexion"})
public class Deconnexion extends HttpServlet

{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out= response.getWriter()){
                HttpSession httpSession = request.getSession();
                //Suppression du cache contenant les informations utilisateur dans la session
                httpSession.removeAttribute("current-user");
                //Définition Flag de suppression du panier pour le JSP
                httpSession.setAttribute("emptyCart","Ok");
                response.sendRedirect("login.jsp");
        }
    }
}
