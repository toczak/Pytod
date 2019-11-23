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

</head>

<body class="text-center">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="index">Pytod - pytaj i odpowiadaj</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">

                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="index">Strona główna
                    </a>
                </li>

                <li class="nav-item">
                    <c:choose>
                        <c:when test="${sessionScope.login==null}">
                            <a class="nav-link" href="zaloguj.jsp">Zaloguj się</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link btn btn-primary" href="wyloguj">Wyloguj</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>

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


</body>
</html>