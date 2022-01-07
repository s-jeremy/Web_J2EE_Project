package com.example.web_j2ee_project.servlets;

import com.example.web_j2ee_project.dao.ArticleDao;
import com.example.web_j2ee_project.dao.FactureDao;
import com.example.web_j2ee_project.dao.UserDao;
import com.example.web_j2ee_project.hibernate.entites.Article;
import com.example.web_j2ee_project.hibernate.entites.Client;
import com.example.web_j2ee_project.hibernate.entites.Facture;
import com.example.web_j2ee_project.panier.ArticleFacturation;
import com.example.web_j2ee_project.panier.FactoryProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet(name = "GenerationFacture", urlPatterns = {"/GenerationFacture"})
public class GenerationFacture extends HttpServlet

{
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        ArticleDao articleDao = new ArticleDao(FactoryProvider.getFactory());
        HttpSession httpSession = request.getSession();
        Client user = (Client)httpSession.getAttribute("current-user");
        String nom = request.getParameter("nom");
        String email = request.getParameter("e-mail");
        String addresse = request.getParameter("addresse");
        response.setContentType( "application/pdf" );
        String masterPath = request.getServletContext().getRealPath( "/WEB-INF/ModelFacture.pdf" );

        String panier = request.getParameter("panier");
        panier = java.net.URLDecoder.decode(panier, StandardCharsets.UTF_8);
        System.out.println("test panier : "+panier);
        System.out.println("test id_user du panier : "+user.getId());
        ObjectMapper mapper = new ObjectMapper();
        ArticleFacturation[] articles = mapper.readValue(panier, ArticleFacturation[].class);
        System.out.println(Arrays.toString(articles));
        try (PdfReader reader = new PdfReader( masterPath );
             PdfWriter writer = new PdfWriter( byteArrayOutputStream );
             PdfDocument document = new PdfDocument( reader, writer ) )
        {
            PdfPage page = document.getPage(1);
            PdfCanvas canvas = new PdfCanvas(page);

            FontProgram fontProgram = FontProgramFactory.createFont();
            PdfFont font = PdfFontFactory.createFont(fontProgram, "utf-8");
            canvas.setFontAndSize(font, 11);

            canvas.beginText();

            canvas.setTextMatrix(143, 675);
            canvas.showText("" + nom);

            canvas.setTextMatrix(185, 653);
            canvas.showText("" + addresse);

            canvas.setTextMatrix(127, 630);
            canvas.showText("" + email);


            int top = 420;
            double totalPrice = 0;
            canvas.setFontAndSize(font, 13);
            NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("fr", "FR"));
            Session hibernateSession = FactoryProvider.getFactory().openSession();
            Transaction transaction = hibernateSession.beginTransaction();
            for (ArticleFacturation articleFacturation : articles)
            {
                Article article = articleDao.getArticle(articleFacturation.getId());
                canvas.setTextMatrix(100, top);
                canvas.showText("" + article.getNomProduit());

                canvas.setTextMatrix(250, top);
                canvas.showText(""+article.getPrixProduit());

                canvas.setTextMatrix(345, top);
                canvas.showText(""+articleFacturation.getQuantiteProduit());

                double totalLigne = articleFacturation.getQuantiteProduit()*article.getPrixProduit();
                canvas.setTextMatrix(445, top);
                canvas.showText(""+totalLigne);


                totalPrice += totalLigne;

                top -= 30;

                article.setQuantiteProduit(article.getQuantiteProduit()-articleFacturation.getQuantiteProduit());
                hibernateSession.update(article);

            }

            canvas.setTextMatrix(443, 155);
            canvas.showText(formatter.format(totalPrice)+" Euro");

            canvas.endText();
            document.close();

            FactureDao factureDao = new FactureDao(FactoryProvider.getFactory());
            Facture facture = new Facture();
            facture.setIdUser(user.getId());

            LocalDate date = LocalDate.now();
            long i = factureDao.getNbFacture(user.getId());
            facture.setDate(date);
            facture.setPdfFile(byteArrayOutputStream.toByteArray());
            facture.setPdfName("Facture-n°"+i+"-"+LocalDate.now()+".pdf");

            System.out.println(facture.toString());

            int savedFactureId = (int)hibernateSession.save(facture);

            transaction.commit();
            hibernateSession.close();
            httpSession.setAttribute("factureStatus","Ok");
            response.sendRedirect("user.jsp");
            transaction.commit();
            hibernateSession.close();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
