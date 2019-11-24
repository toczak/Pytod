<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 24.11.2019
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

            <c:choose>
                <c:when test="${fn:length(postList)==0}">
                    <h1 class="my-4">
                        Brak odpowiedzi użytkownika "${nick}"!
                    </h1>
                </c:when>
                <c:otherwise>
                    <h1 class="my-4">
                        Lista odpowiedzi użytkownika "${nick}":
                    </h1>

                    <c:forEach items="${postList}" var="list">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h2 class="card-title">Odpowiedź z pytania ${list.idPost}:</h2>
                                <p class="card-text">${list.text}</p>
                                <div class="d-flex justify-content-between">
                                    <p class="card-text">
                                    </p>
                                    <a href="pytanie?id=${list.idPost}" class=" btn btn-primary">Przejdź do pytania
                                        &rarr;</a>
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

                                <a class="page-link"
                                   href="lista-odpowiedzi?nick=${fraza}&strona=${currentPage-1}">Poprzednia</a>
                            </li>
                            <li class="page-item active"><span class="page-link">
                                    ${currentPage}
                            </span></li>
                            <li class="page-item <c:if test="${currentPage eq maxPage}">disabled</c:if>">
                                <a class="page-link"
                                   href="lista-odpowiedzi?nick=${fraza}&strona=${currentPage+1}">Następna</a>
                            </li>
                        </ul>
                    </nav>


                </c:otherwise>
            </c:choose>


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
