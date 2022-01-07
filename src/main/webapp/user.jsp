<%@ page import="javax.xml.stream.FactoryConfigurationError" %>
<%@ page import="com.example.web_j2ee_project.dao.FactureDao" %>
<%@ page import="com.example.web_j2ee_project.panier.FactoryProvider" %>
<%@ page import="com.example.web_j2ee_project.hibernate.entites.Facture" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 23/12/2021
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%
    Client clientTest = (Client)session.getAttribute("current-user");
    if (clientTest==null){
        session.setAttribute("notification","Vous n'êtes pas identfié !");
        response.sendRedirect("login.jsp");
        return;
    }
    Object factureStatus = session.getAttribute("factureStatus");
    if (factureStatus!=null){
        if (factureStatus=="Ok"){
            session.removeAttribute("factureStatus");
        %>
        <script>localStorage.removeItem("panier");</script>
        <%}
    }
    FactureDao factureDao = new FactureDao(FactoryProvider.getFactory());
    List<Facture> listFacture = factureDao.getFactures(clientTest.getId());%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Compte</title>
        <%@include file="components/standard_js_css.jsp"%>
    </head>
    <body>
    <%@include file="components/navbar.jsp"%>
    <%@include file="components/standard_modal.jsp"%>
        <div class="container">
            <div class="row mt-4">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center mb-5">Historique des commandes</h3>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Date de commande</th>
                                    <th scope="col">Facture</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    int i = 1;
                                    for(Facture facture: listFacture){
                                %>
                                <tr>
                                    <th scope="row"><%= i %></th>
                                    <td><%= facture.getDate() %></td>
                                    <td>
                                        <form method="post" action="DownloadFacture">
                                            <input type="hidden" name="file_id" value="<%=facture.getId()%>">
                                            <button ><%= facture.getPdfName()%></button>
                                        </form>
                                    </td>
                                </tr>
                                <%i     +=1;
                                    }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
