<%@ page import="com.example.web_j2ee_project.hibernate.entites.Categorie" %>
<%@ page import="com.example.web_j2ee_project.dao.ArticleDao" %>
<%@ page import="com.example.web_j2ee_project.panier.FactoryProvider" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.web_j2ee_project.hibernate.entites.Article" %>
<%@ page import="com.example.web_j2ee_project.dao.CategorieDao" %>
<%@ page import="com.example.web_j2ee_project.panier.Helper" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <title>Market</title>
  <%@include file="components/standard_js_css.jsp"%>
</head>
<body>

<%@include file="components/navbar.jsp"%>

<div align="center">
  <br><h1><%= "Market" %></h1>
</div>

<div class="row mt-3 mx-2">
  <%
    ArticleDao articleDao = new ArticleDao(FactoryProvider.getFactory());
    List<Article> listArticle = articleDao.getArticles();
    CategorieDao categorieDao = new CategorieDao(FactoryProvider.getFactory());
    List<Categorie> listCategorie = categorieDao.getCategories();
  %>

  <%-- Catégorie --%>
  <div class="col-md-2">
    <div class="list-group mt-4">
      <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
        Tous les produits
      </a>
      <%
        for(Categorie categorie : listCategorie){
      %>
          <!--out.println(categorie.getTitre() + "<br>");-->
          <a href="#" class="list-group-item list-group-item-action"><%= categorie.getTitre() %></a>
      <%
        }
      %>
    </div>
  </div>

  <%-- Produits --%>
  <div class="col-md-8 mx-4">
    <div class="row mt-4">
          <%
            for(Article article: listArticle){
          %>
          <div class="card mb-5 mx-4" style="width: 18rem;">
            <div class="card-body">
              <h5 class="card-title"><%= article.getNomProduit() %></h5>
              <p class="card-text"><%= Helper.getSomeWords(article.getDescriptionProduit()) %></p>
            </div>
            <div class="card-footer">
              <button class="btn custom-bg text-white">Ajouter</button>
              <button class="btn btn-outline-primary"><%= article.getPrixProduit() %> &#8364;</button>
            </div>
          </div><br>
          <%
            }
          %>
    </div>
  </div>

</div>

</body>
</html>
