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
    <title>Title</title>
      <%@include file="components/standard_js_css.jsp"%>
  </head>
  <body>
    <%@include file="components/navbar.jsp"%>
    <h1>Role : Administrateur.</h1>
  </body>
</html>
