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
    <link rel="shortcut icon" href="res/icon.ico?">

</head>

<body>

<jsp:include page="include/navbar.jsp"/>

<div class="container mt-2 py-5">

    <div class="row">


        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">
                Lista znalezionych pytań:
            </h1>

            <!-- Blog Post -->
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

            <nav aria-label="Panel nawigacji">
                <ul class="pagination justify-content-center">
                    <li class="page-item <c:if test="${currentPage==1}">disabled</c:if>">

                        <a class="page-link" href="szukaj?fraza=${fraza}&strona=${currentPage-1}">Poprzednia</a>
                    </li>
                    <li class="page-item active"><span class="page-link">
                        ${currentPage}
                    </span></li>
                    <li class="page-item <c:if test="${currentPage eq maxPage}">disabled</c:if>">
                        <a class="page-link" href="szukaj?fraza=${fraza}&strona=${currentPage+1}">Następna</a>
                    </li>
                </ul>
            </nav>

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
