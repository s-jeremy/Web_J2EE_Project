<%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 06/12/2021
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%@ page import="com.example.web_j2ee_project.hibernate.entites.Client" language="java" %>
<%

  Client client = (Client)session.getAttribute("current-user");
%>

<nav class="navbar navbar-expand-lg navbar-light custom-bg">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">E-phone Market</a>     <!-- old href ==> "#" -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Magasin</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#" onclick="window.open('admin.jsp','_self');
          <%--window.open('user.jsp','_self');--%>">Compte</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled">Jeremy et Mehdi</a>
        </li>
      </ul>

      <ul class="navbar-nav ml-auto">
        <%
          if (client==null){

        %>

        <li class="nav-item active">
          <a class="nav-link" href="login.jsp">Connexion</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="register.jsp">Inscription</a>
        </li>
        <%
          }else{
        %>
        <li class="nav-item active">
          <a class="nav-link" href="#"><%= client.getUsername()  %></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="Deconnexion">Déconnexion</a>
        </li>
        <%
          }
        %>
      </ul>

    </div>
  </div>
</nav>
