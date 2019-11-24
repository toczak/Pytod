<%@ page import="Model.User" %>
<%@ page import="JSON.JSONUser" %><%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 24.11.2019
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${sessionScope.login==null}">
    <c:redirect url="index"/>
</c:if>
<!DOCTYPE html>
<html lang="pl">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pytod - pytaj i odpowiadaj</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">
    <link rel="shortcut icon" href="res/icon.ico?">

</head>

<body>

<jsp:include page="include/navbar.jsp"/>

<div class="container mt-2 py-5">

    <div class="row">


        <div class="col-md-8">
            <% User user = JSONUser.getUserInfo((String) session.getAttribute("login")); %>
            <ul class="list-group">
                <li class="list-group-item active"> Użytkownik: <%=user.getUsername()%>
                </li>
                <li class="list-group-item">E-mail: <%=user.getEmail()%>
                </li>
                <li class="list-group-item">Hasło: <%=user.getPassword()%>
                </li>
            </ul>
        </div>

        <%@ include file="include/panel.jsp" %>

    </div>
</div>

<%@ include file="include/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

