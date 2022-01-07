<%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 23/12/2021
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%@ page import="com.example.web_j2ee_project.hibernate.entites.Client" language="java" %>
<%@ page import="com.example.web_j2ee_project.dao.CategorieDao" %>
<%@ page import="com.example.web_j2ee_project.panier.FactoryProvider" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.web_j2ee_project.hibernate.entites.Categorie" %>
<%@ page import="com.example.web_j2ee_project.dao.UserDao" %>
<%@ page import="com.example.web_j2ee_project.dao.ArticleDao" %>
<%@ page import="com.example.web_j2ee_project.hibernate.entites.Article" %>
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
      <link rel="stylesheet" type="text/css" href="css/style.css" />
      <title>Admin</title>
      <%@include file="components/standard_js_css.jsp"%>
  </head>
  <body>
    <%@include file="components/navbar.jsp"%>
    <div style="text-align:center">
        <h1>Menu Administrateur</h1>
        <br><br><br>
    </div>
        <div class="container admin">
            <div class="container-fluid">
                <%@include file="components/notification.jsp" %>
            </div>
            <div class="row">
                <div class="col">
                    <div class="card" data-bs-toggle="modal" data-bs-target="#view-users">
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
                    <div class="card" data-bs-toggle="modal" data-bs-target="#view-category">
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
                    <div class="card" data-bs-toggle="modal" data-bs-target="#add-category">
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

            </div>
            <div class="row">
                <div class="col">
                    <div class="card" data-bs-toggle="modal" data-bs-target="#view-product">
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
                    <div class="card" data-bs-toggle="modal" data-bs-target="#add-product">
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
                <div class="col">
                    <div class="card" data-bs-toggle="modal" data-bs-target="#modify-stock">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 125px;" class="img-fluid rounded-circle border" src="img/edit.png">
                            </div>
                            <br><br>
                            <h5 class="text-uppercase text-muted">Modifier Stock Produit</h5>
                            <p class="mt-2" style="font-size:50%;">Clique ici pour modifier les stocks d'un produit.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

  <%-- Boostrap Modal Component --%>
    <%-- Ajouter une catégorie --%>
    <!-- Modal -->
    <div class="modal fade" id="add-category" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Remplir les informations de la catégorie</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="OperationAdmin" method="post">
                        <input type="hidden" name="operation" value="ajoutCategorie">
                        <div class="form-group">
                            <input type="text" class="form-control" name="categorie_titre" placeholder="Entrer le nom de la catégorie" required><br>
                        </div>
                        <div class="form-group">
                            <textarea type="text" class="form-control" name="categorie_description" placeholder="Entrer le descriptif de la catégorie" required></textarea><br>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Ajouter</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%-- Ajouter un produit --%>
    <!-- Modal -->
    <div class="modal fade" id="add-product" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" class="exampleModalLabel1">Remplir les informations du produit</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="OperationAdmin" method="post">
                        <input type="hidden" name="operation" value="ajoutArticle">
                        <div class="form-group">
                            <input type="text" class="form-control" name="article_nom" placeholder="Entrer le nom du produit" required><br>
                        </div>
                        <div class="form-group">
                            <textarea type="text" class="form-control" name="article_description" placeholder="Entrer le descriptif du produit" required></textarea><br>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control" name="article_prix" placeholder="Entrer le prix du produit (en €)" required><br>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control" name="article_quantite" placeholder="Entrer la quantité du produit" required><br>
                        </div>
                        <%-- Categories --%>
                        <%
                            CategorieDao categorieDao = new CategorieDao(FactoryProvider.getFactory());
                            List<Categorie> list = categorieDao.getCategories();
                        %>
                        <div class="form-group">
                            <select class="form-select" name="categorie_id" aria-label="Default select example">
                                <option selected>Choisir la catégorie du produit</option>
                                <%
                                    for(Categorie categorie:list){
                                %>
                                <option value="<%= categorie.getId()%>"><%= categorie.getTitre()%></option>
                                <%
                                    }
                                %>
                            </select><br>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Ajouter</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%-- Aperçu des utilisateurs --%>
    <!-- Modal -->
    <div class="modal fade" id="view-users" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" class="exampleModalLabel1">Remplir les informations au sujet des utilisateurs</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="OperationAdmin" method="post">
                        <input type="hidden" name="operation" value="bloquerUtilisateur">
                        <%-- List utilisateur --%>
                        <%
                            UserDao userDao = new UserDao(FactoryProvider.getFactory());
                            List<Client> listUser = userDao.getUsers();
                        %>
                        <div class="form-group">
                            <select class="form-select" name="utilisateur_id" aria-label="Default select example">
                                <option selected>Choisir l'utilisateur</option>
                                <%
                                    for(Client user:listUser){
                                %>
                                <option value="<%= user.getId()%>"><%= user.getUsername()%> - Status = <%
                                    if (user.getBloquer()==1){
                                            %>Bloquer
                                    <%}else{%>Débloquer<%} %>
                                </option>
                                <%
                                    }
                                %>
                            </select><br>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success"> Bloquer / Débloquer</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%-- Aperçu Categorie --%>
    <!-- Modal -->
    <div class="modal fade" id="view-category" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" class="exampleModalLabel1">Remplir les informations des catégories</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="OperationAdmin" method="post">
                        <input type="hidden" name="operation" value="supprimerCategorie">
                        <%-- Categories --%>
                        <%
                            list = categorieDao.getCategories();
                        %>
                        <div class="form-group">
                            <select class="form-select" name="categorie_id" aria-label="Default select example">
                                <option selected>Choisir la catégorie à supprimer</option>
                                <%
                                    for(Categorie categorie:list){
                                %>
                                <option value="<%= categorie.getId()%>"><%= categorie.getTitre()%></option>
                                <%
                                    }
                                %>
                            </select><br>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Supprimer</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%-- Aperçu produit --%>
    <!-- Modal -->
    <div class="modal fade" id="view-product" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" class="exampleModalLabel1">Remplir les informations des produits</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="OperationAdmin" method="post">
                        <input type="hidden" name="operation" value="supprimerArticle">

                        <%-- Produit --%>
                        <%

                            ArticleDao articleDao = new ArticleDao(FactoryProvider.getFactory());
                            List<Article> listArticle = articleDao.getArticles();
                        %>
                        <div class="form-group">
                            <select class="form-select" name="article_id" aria-label="Default select example">
                                <option selected>Choisir le produit à supprimer</option>
                                <%
                                    for(Article article:listArticle){
                                %>
                                <option value="<%= article.getId()%>"><%= article.getNomProduit()%> - Stock : <%= article.getQuantiteProduit()%></option>
                                <%
                                    }
                                %>
                            </select><br>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Supprimer</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%-- Modifier Stock --%>
    <!-- Modal -->
    <div class="modal fade" id="modify-stock" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" class="exampleModalLabel1">Remplir les informations des stocks</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="OperationAdmin" method="post">
                        <input type="hidden" name="operation" value="modifierStockArticle">
                        <%-- Produit --%>
                        <div class="form-group">
                            <select class="form-select" name="article_id" aria-label="Default select example">
                                <option selected>Choisir le produit</option>
                                <%
                                    for(Article article:listArticle){
                                %>
                                <option value="<%= article.getId()%>"><%= article.getNomProduit()%> - Stock : <%= article.getQuantiteProduit()%></option>
                                <%
                                    }
                                %>
                            </select><br>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control" name="quantite" placeholder="Entrer la quantité du produit" required><br>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Modifier</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
  
  <%@include file="components/standard_modal.jsp"%>

  </body>
</html>
