<%--
  Created by IntelliJ IDEA.
  User: jeremyselo
  Date: 13/12/2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%
  String notification = (String)session.getAttribute("notification");
  if(notification != null){
    //out.println(notification);

%>

<div class="alert alert-success alert-dismissible fade show" role="alert">
  <strong><%= notification%></strong>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<%    session.removeAttribute("notification");
  }

%>
