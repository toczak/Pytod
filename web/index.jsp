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
<div class="container">
    ${komunikat}

    <div class="row">


        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">
                Lista pytań
            </h1>

            <!-- Blog Post -->
            <%--<div class="card mb-4">--%>
            <%--<div class="card-body">--%>
            <%--<h2 class="card-title">Pytanie 1:</h2>--%>
            <%--<p class="card-text">Jak się szybko uczyć? Pomóżcie!!!!!!</p>--%>
            <%--<div class="d-flex justify-content-between">--%>
            <%--<p class="card-text">--%>
            <%--<small class="text-muted">Liczba udzielonych odpowiedzi: 5</small>--%>
            <%--</p>--%>
            <%--<a href="pytanie.jsp" class=" btn btn-primary">Przejdź do pytania &rarr;</a>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="card-footer">--%>
            <%--<small class="text-muted">Data dodania: 05/04/2019</small>--%>
            <%--</div>--%>
            <%--</div>--%>

            <c:forEach items="${postList}" var="list">
                <div class="card mb-4">
                    <div class="card-body">
                        <h2 class="card-title">Pytanie ${list.id}:</h2>
                        <p class="card-text">${list.textQuestion}</p>
                        <div class="d-flex justify-content-between">
                            <p class="card-text">
                                <small class="text-muted">Liczba udzielonych odpowiedzi: ${list.countAnswers}</small>
                            </p>
                            <a href="pytanie?id=${list.id}" class=" btn btn-primary">Przejdź do pytania &rarr;</a>
                        </div>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">Data dodania: ${list.date}</small>
                    </div>
                </div>
            </c:forEach>


            <%--<jsp:include page="ListPost" />--%>

            <nav aria-label="Panel nawigacji">
                <ul class="pagination justify-content-center">
                    <li class="page-item <c:if test="${currentPage==1}">disabled</c:if>">

                        <a class="page-link" href="index?strona=${currentPage-1}">Poprzednia</a>
                    </li>
                    <li class="page-item active"><span class="page-link">
                        ${currentPage}
                    </span></li>
                    <%--<li class="page-item"><a class="page-link" href="#">2</a></li>--%>
                    <%--<li class="page-item"><a class="page-link" href="#">3</a></li>--%>
                    <li class="page-item <c:if test="${currentPage eq maxPage}">disabled</c:if>">
                        <a class="page-link" href="index?strona=${currentPage+1}">Następna</a>
                    </li>
                </ul>
            </nav>

        </div>


        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Search Widget -->
            <%@ include file="SearchWidget.jsp" %>


            <div class="card my-4">
                <h5 class="card-header">Opcje</h5>
                <div class="card-body">
                    <c:if test="${sessionScope.login!=null}">
                        <a class="btn btn-success btn-block" href="zadaj.jsp">Zadaj pytanie</a>
                        <br/>
                    </c:if>
                    <div class="alert alert-dark text-center" role="alert">
                        Aktualnie użytkowników: <span class="badge badge-light">1</span>

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
