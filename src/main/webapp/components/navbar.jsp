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
          <%
            if (client!=null){
              if (client.getRole().equals("user")){
          %>
          <a class="nav-link active" aria-current="page" href="#" onclick="window.open('market.jsp','_self');">Magasin</a>
          <%
              }
            }
          %>
        </li>
        <li class="nav-item">
          <%
            if (client!=null){
              if (client.getRole().equals("admin")){

          %>
          <a class="nav-link active" aria-current="page" href="#" onclick="window.open('admin.jsp','_self');">Compte</a>
          <%
              }else if(client.getRole().equals("user")){
          %>
          <a class="nav-link active" aria-current="page" href="#" onclick="window.open('user.jsp','_self');">Compte</a>
          <%
              }
            }
          %>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled">Realise par Jeremy et Mehdi</a>
        </li>
      </ul>

      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <%
            if (client!=null){
              if (client.getRole().equals("user")){
          %>
          <a class="nav-link" href="#!" data-toggle="modal" data-target="#panier">
            <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-cart-plus" viewBox="0 0 16 16">
              <path d="M9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"/>
              <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
            </svg>
            <span class="ml-0 cart-items">(0)</span>
          </a>
          <%
              }
            }
          %>

        </li>
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
          <a class="nav-link" href="Deconnexion">Deconnexion</a>
        </li>
        <%
          }
        %>
      </ul>

    </div>
  </div>
</nav>
