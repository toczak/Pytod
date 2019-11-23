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
    <link rel="shortcut icon" href="res/icon.ico?">

</head>

<body>

<jsp:include page="include/navbar.jsp"/>

<div class="container mt-2 py-5 mb-3">

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


        <%@ include file="include/panel.jsp" %>

    </div>
    <!-- /.row -->

</div>

<%@ include file="include/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
