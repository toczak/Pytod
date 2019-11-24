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

<div class="container mt-2 py-5 mb-3">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1>
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


        <%@ include file="include/panel.jsp" %>

    </div>
    <!-- /.row -->

</div>
<footer>
<%@ include file="include/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
