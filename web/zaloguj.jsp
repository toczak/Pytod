<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.login!=null}">
    <c:redirect url="index"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Pytod - Logowanie</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="res/icon.ico?">

</head>

<body class="text-center">
<jsp:include page="include/navbar.jsp"/>

<form class="form-signin" method="post" action="logowanie">
    <h1 class="h3 mb-3 font-weight-normal">Logowanie</h1>
    <input type="text" name="inputLogin" class="form-control" placeholder="Login / adres e-mail" required=""
           autofocus="">
    <input type="password" name="inputPassword" class="form-control" placeholder="Hasło" required="">
    <!--<div class="checkbox mb-2">-->
    <!--<label>-->
    <!--<input type="checkbox" value="remember-me"> Pamiętaj mnie-->
    <!--</label>-->
    <!--</div>-->
    <input class="btn btn-lg btn-primary btn-block" value="Zaloguj" type="submit">
    <a class="nav-link" href="rejestracja.jsp">Nie masz konta? Zarejestruj się.</a>
    <!--<p class="mt-5 mb-3 text-muted">© 2017-2018</p>-->
</form>

<%--<%@ include file="include/footer.jsp" %>--%>

</body>
</html>