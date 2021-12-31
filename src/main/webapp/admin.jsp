<%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 23/12/2021
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.example.web_j2ee_project.hibernate.entites.Client" language="java" %>
<%
    Client clientAdmin = (Client)session.getAttribute("current-user");
    if (clientAdmin==null){
      session.setAttribute("notification","Vous n'êtes pas identfié !");
      response.sendRedirect("login.jsp");
      return;
    }else{
        if (clientAdmin.getRole().equals("user")){
            session.setAttribute("notification","Vous n'êtes pas autoriser à accéder à cette page !");
            response.sendRedirect("login.jsp");
            return;
        }
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Admin</title>
      <%@include file="components/standard_js_css.jsp"%>
  </head>
  <body>
    <%@include file="components/navbar.jsp"%>
    <div style="text-align:center">
        <h1>Menu Administrateur</h1>
        <br><br><br>
    </div>
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid rounded-circle border" src="img/user1.png">
                            </div>
                            <br><br>
                            <h5 class="text-uppercase text-muted">Utilisateurs</h5>
                            <p class="mt-2" style="font-size:50%;">Clique ici pour apercevoir les utilisateurs.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid rounded-circle border" src="img/categories.png">
                            </div>
                            <br><br>
                            <h5 class="text-uppercase text-muted">Catégories</h5>
                            <p class="mt-2" style="font-size:50%;">Clique ici pour apercevoir les catégories.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid rounded-circle border" src="img/add-category.png">
                            </div>
                            <br><br>
                            <h5 class="text-uppercase text-muted">Ajout catégorie</h5>
                            <p class="mt-2" style="font-size:50%;">Clique ici pour ajouter une nouvelle catégorie.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid rounded-circle border" src="img/product.png">
                            </div>
                            <br><br>
                            <h5 class="text-uppercase text-muted">Produits</h5>
                            <p class="mt-2" style="font-size:50%;">Clique ici pour apercevoir les produits.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid rounded-circle border" src="img/add-product.png">
                            </div>
                            <br><br>
                            <h5 class="text-uppercase text-muted">Ajout produit</h5>
                            <p class="mt-2" style="font-size:50%;">Clique ici pour ajouter un nouveau produit.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
  </body>
</html>
