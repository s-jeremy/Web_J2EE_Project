package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.dao.FactureDao;
import com.example.web_j2ee_project.dao.UserDao;
import com.example.web_j2ee_project.hibernate.entites.Client;
import com.example.web_j2ee_project.hibernate.entites.Facture;
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

@WebServlet(name = "DownloadFacture", urlPatterns = {"/DownloadFacture"})
public class DownloadFacture extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        //Définition de l'action de téléchargement d'un fichier binaire
        //Le fichier pdf est stocker en binaire dans la BDD
        response.setContentType("application/force-download");
        response.setHeader("Content-Transfer-Encoding", "binary");
        //Récupération de l'id du fichier à télécharger
        int file_id = Integer.valueOf(request.getParameter("file_id"));
        try{
            //Récupération de la facture via l'id
            FactureDao factureDao = new FactureDao(FactoryProvider.getFactory());
            Facture facture = factureDao.getFacture(file_id);
            //Définition du nom du fichier lors du téléchargement
            response.setHeader("Content-Disposition","attachment; filename="+facture.getPdfName());
            //Ecriture du fichier dans la réponse et lancement du téléchargement
            response.getOutputStream().write(facture.getPdfFile());
            response.setContentLength(facture.getPdfFile().length);
            response.getOutputStream().write(facture.getPdfFile());
            response.getOutputStream().close();
            response.flushBuffer();

        } catch (Exception e){
            System.out.println(e.getMessage());
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("notification","Une erreur est survenu !");
            response.sendRedirect("register.jsp");
            return;
        }
    }
}
