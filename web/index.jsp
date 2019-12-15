<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${currentPage==null}">
    <c:redirect url="index"/>
</c:if>
<c:if test="${sessionScope.mustChangePassword==true}">
    <c:redirect url="zmien-haslo.jsp"/>
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

<div class="container mt-2 py-5">
    ${komunikat}

    <div class="row">
        <jsp:include page="include/main-content.jsp"/>
        <jsp:include page="include/panel.jsp"/>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
<%
    if (session.getAttribute("login") != null) {
        if ((boolean) session.getAttribute("mustShowAlert")) { %>
<script type="text/javascript">
    alert("Komunikat od administracji:\n<%=session.getAttribute("alertText")%>");
</script>
<%
            session.setAttribute("mustShowAlert", false);
        }
    }
%>

</html>
