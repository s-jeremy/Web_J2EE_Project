<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>E-phone Market</title>
    <%@include file="components/standard_js_css.jsp"%>
</head>
<body>

    <%@include file="components/navbar.jsp"%>

    <div align="center">
        <br><h1><%= "E-phone Market" %></h1>
        <br><p>Site de e-commerce sur la vente de téléphones. Inscrivez-vous et/ou connectez-vous afin d'acheter votre futur téléphone.</p>
        <p>Dans la limite des ventes (Quantités limités) !</p>
        <%-- Animation récupérée depuis le site LottieFiles --%>
        <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
        <lottie-player src="https://assets6.lottiefiles.com/packages/lf20_tiulipe2.json"  background="transparent"  speed="0.5"  style="width: 600px; height: 600px;"  loop  autoplay></lottie-player>
    </div>

<br/>
</body>
</html>