<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

</head>

<body>

<!-- Navigation -->
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

                <c:choose>
                    <c:when test="${sessionScope.login==null}">
                        <a class="nav-link" href="zaloguj.jsp">Zaloguj się</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-secondary" href="wyloguj">Wyloguj</a>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container mb-3">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">
                Pytanie ${id}:
            </h1>

            <!-- Blog Post -->
            <div class="card mb-4 ${color} border-dark">
                <div class="card-body">
                    <div class="row">
                        <p class="card-text col-9">${question}</p>
                        <div class="ml-auto col-3 border-left">
                            <p class="ml-auto">Data dodania:
                                <br/><i>${date}</i></p>
                            <p class="ml-auto">Użytkownik:<br/>
                                <b>${author}</b>
                            </p>

                        </div>
                    </div>
                </div>
            </div>

            <%--<div class="card mb-4">--%>
                <%--<div class="card-header">--%>
                    <%--Użytkownik <b>hetheqwe</b> napisał dnia <i>06/04/2019</i>:--%>
                <%--</div>--%>
                <%--<div class="card-body">--%>
                    <%--<p class="card-text">Użyj mózgu kolego xDD</p>--%>
                <%--</div>--%>
            <%--</div>--%>
            <c:forEach items="${answerList}" var="list">
            <div class="card mb-4">
                <div class="card-header">
                    Użytkownik <b>${list.author}</b> napisał dnia <i>${list.date}</i>:
                </div>
                <div class="card-body">
                    <p class="card-text">${list.text}</p>
                </div>
            </div>
            </c:forEach>
            <c:if test="${sessionScope.login!=null}">
                <div class="card">
                    <div class="card-body">
                        <form method="post" action="dodaj-odpowiedz">
                        <h5 class="card-title">Odpowiedz</h5>
                        <textarea class="mb-3 form-control" name="textareaAnswer" rows="3"></textarea>
                            <input type="hidden" name="id" value="${id}"/>
                        <input type="submit" class="btn btn-primary float-right" value="Odpowiedz"/>
                        </form>
                    </div>
                </div>
            </c:if>


        </div>


        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Search Widget -->
            <%@ include file = "SearchWidget.jsp" %>

            <div class="card my-4">
                <h5 class="card-header">Opcje</h5>
                <div class="card-body">
                    <c:if test="${sessionScope.login!=null}">
                        <a class="btn btn-success btn-block" href="zadaj.jsp">Zadaj pytanie</a>
                        <br/>
                    </c:if>
                    <div class="alert alert-dark text-center" role="alert">
                        Zalogowani użytkownicy:
                        <span class="badge badge-light">
                            ${applicationScope['count']}
                        </span>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Patryk Potoczak 2019</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
