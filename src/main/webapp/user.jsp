<%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 23/12/2021
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%
    Client clientAdmin = (Client)session.getAttribute("current-user");
    if (clientAdmin==null){
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
    }%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Compte</title>
        <%@include file="components/standard_js_css.jsp"%>
    </head>
    <body>
    <%@include file="components/navbar.jsp"%>
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
                                <tr>
                                    <th scope="row">1</th>
                                    <td>...</td>
                                    <td>...</td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>...</td>
                                    <td>...</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
