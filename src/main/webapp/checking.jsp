<%
    Client user = (Client)session.getAttribute("current-user");
    if (user==null){
        session.setAttribute("notification","Vous n'êtes pas connecté ! Première connexion pour accéder à la page commande !");
        response.sendRedirect("login.jsp");
        return;
    }
%>

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Commande</title>
        <%@include file="components/standard_js_css.jsp"%>
    </head>
    <body>
    <%@include file="components/navbar.jsp"%>

    <div class="container">
        <div class="row mt-5">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="text-center mb-5">Votre commande</h3>
                        <div class="cart-body">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="text-center mb-5">Vos coordonnées</h3>
                        <form action="GenerationFacture" method="post">
                            <input type="hidden" id="achatPanier" name="panier" value="placeholder_panier">
                            <input type="hidden" name="panier" value="<%=user.getId() %>">
                            <div class="form-group">
                                <label for="email">Votre adresse</label>
                                <input value="<%= user.getMail()%>" type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Entrer votre email">
                                <small id="emailHelp" class="form-text text-muted"></small>
                            </div><br>
                            <div class="form-group">
                                <label for="name">Votre nom</label>
                                <input value="<%= user.getUsername()%>" type="text" class="form-control" id="name" aria-describedby="nameHelp" placeholder="Entrer votre nom">
                                <small id="nameHelp" class="form-text text-muted"></small>
                            </div><br>
                            <div class="form-group">
                                <label for="address">Votre adresse de livraison</label>
                                <textarea value="<%= user.getAdresse()%>" class="form-control" id="address" placeholder="Entrer votre adresse de livraison" rows="3"></textarea>
                            </div><br>
                            <div class="container text-center">
                                <button class="btn btn-outline-success">Commander</button>
                                <button class="btn btn-outline-primary checkout-btn"><a href="market.jsp" class="text-blue" style="text-decoration:none">Continuer vos achats</a></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        var s = document.getElementById("achatPanier")
        s.value = localStorage.getItem("panier")
    </script>
    <%@include file="components/standard_modal.jsp"%>

</body>
</html>
