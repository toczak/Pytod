<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
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

<form class="form-signin" method="post" action="rejestracja">
    <h1 class="h3 mb-3 font-weight-normal">Rejestracja</h1>
    <input type="text" name="inputNick" class="form-control mb-2" placeholder="Login" required="" autofocus="">
    ${resultName}
    <input type="email" name="inputEmail" class="form-control mb-2" placeholder="Adres e-mail" required="" autofocus="">
    ${resultEmail}
    <input type="password" name="inputPassword" class="form-control mb-2" placeholder="Hasło" required="">
    ${resultPassword}
    <input type="password" name="inputPassword2" class="form-control mb-2" placeholder="Powtórz hasło" required="">
    ${resultPassword2}
    <input class="btn btn-lg btn-primary btn-block" value="Zarejestruj" type="submit"/>
</form>

</body>
</html>