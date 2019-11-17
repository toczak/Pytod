<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.login==null}">
    <c:redirect url="index.jsp"/>
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
            </h1>

            <!-- Blog Post -->

            <div class="card">
                <div class="card-body">
                    <form method="post" action="dodaj-post">
                    <h5 class="card-title">Wpisz swoje pytanie:</h5>
                    <textarea class="mb-3 form-control" name="textareaQuestion" rows="3"></textarea>
                    <input type="submit" class="btn btn-primary float-right" value="Zadaj"/>
                    </form>
                </div>
            </div>


        </div>


        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Search Widget -->
            <div class="card my-4">
                <h5 class="card-header">Znajdź pytanie</h5>
                <div class="card-body">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Szukana fraza...">
                        <span class="input-group-btn">
                                           <a class="btn btn-secondary" href="wyszukiwanie.jsp">Szukaj</a>

              </span>
                    </div>
                </div>
            </div>

            <div class="card my-4">
                <h5 class="card-header">Opcje</h5>
                <div class="card-body">
                    <a class="btn btn-success btn-block" href="zadaj.jsp">Zadaj pytanie</a>
                    <br/>
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
