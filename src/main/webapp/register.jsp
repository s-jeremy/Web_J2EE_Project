<%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 13/12/2021
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nouveau utilisateur</title>
    <%@include file="components/standard_js_css.jsp"%>
</head>
<body>

    <%@include file="components/navbar.jsp"%>
    <div class="row mt-5">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <%@include file="components/notification.jsp"%>
                <div class="card-body">

                    <h3 class="text-center my-3">
                        Inscription
                    </h3>

                    <form action="Inscription" method="post">
                        <div class="mb-3">
                            <label for="inputUsername" class="form-label">Identifiant</label>
                            <input name="user_name" type="text" class="form-control" id="inputUsername" placeholder="Entrer votre pseudo" aria-describedby="usernameHelp">
                            <div id="usernameHelp" class="form-text">Il s'agit de votre pseudo pour vous connecter à la plateforme.</div>
                        </div>
                        <div class="mb-3">
                            <label for="inputPassword" class="form-label">Mot de Passe</label>
                            <input name="user_password" type="password" class="form-control" id="inputPassword" placeholder="Entrer votre mot de passe" aria-describedby="passwordHelp">
                            <div id="passwordHelp" class="form-text">Pour plus de sécurité, mettez lettres, chiffres, caractères en MAJ/MIN.</div>
                        </div>
                        <div class="mb-3">
                            <label for="inputEmail" class="form-label">Adresse Mail</label>
                            <input name="user_email" type="email" class="form-control" id="inputEmail" placeholder="Entrer votre adresse mail" aria-describedby="emailHelp">
                            <div id="emailHelp" class="form-text">Bien rentré une adresse mail sous cette forme : yyy@xxx.xx.</div>
                        </div>
                        <div class="mb-3">
                            <label for="inputName" class="form-label">Nom</label>
                            <input name="user_name" type="text" class="form-control" id="inputName" placeholder="Entrer votre nom" aria-describedby="nameHelp">
                        </div>
                        <div class="mb-3">
                            <label for="inputFirstName" class="form-label">Prénom</label>
                            <input name="user_surname" type="text" class="form-control" id="inputFirstName" placeholder="Entrer votre nom" aria-describedby="firstNameHelp">
                        </div>
                        <div class="mb-3">
                            <label for="inputAddress" class="form-label">Adresse</label>
                            <textarea name="user_address" type="text" class="form-control" id="inputAddress" placeholder="Entrer votre adresse" aria-describedby="addressHelp"></textarea>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">S'inscrire</button>
                            <button class="btn btn-outline-warning">Réinitialiser</button>
                        </div>

                    </form>

                </div>

            </div>

        </div>
    </div>

</body>
</html>
