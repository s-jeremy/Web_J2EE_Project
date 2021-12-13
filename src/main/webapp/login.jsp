<%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 13/12/2021
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Se connecter</title>
    <%@include file="components/standard_js_css.jsp"%>
</head>
<body>

    <%@include file="components/navbar.jsp"%>
    <div class="container">
      <div class="row mt-4">
        <div class="col-md-4 offset-md-4">
          <div class="card">
            <div class="card-body">

                <h3 class="text-center my-3">
                    Connexion
                </h3>

            <form action="Connexion" method="post">
              <div class="mb-3">
                  <label for="inputUsername" class="form-label">Identifiant</label>
                  <input name="user_name" type="text" class="form-control" id="inputUsername" placeholder="Entrer votre pseudo" aria-describedby="usernameHelp">
              </div>
              <div class="mb-3">
                  <label for="inputPassword" class="form-label">Mot de Passe</label>
                  <input name="user_password" type="password" class="form-control" id="inputPassword" placeholder="Entrer votre mot de passe" aria-describedby="passwordHelp">
              </div>

                <a href="register.jsp" class="text-center d-block mb-2">Si vous n'avez pas de compte, cliquez ici.</a>

              <div class="container text-center">
                  <button class="btn btn-outline-success">Se connecter</button>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
