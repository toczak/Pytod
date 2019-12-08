<%@ page import="Model.User" %>
<%@ page import="JSON.JSONUser" %><%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 24.11.2019
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:if test="${sessionScope.login==null}">
    <c:redirect url="index"/>
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

    <div class="row">


        <div class="col-md-8">
            <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                               url="jdbc:mysql://localhost:3306/pytod" user="root" password=""/>
            <sql:query dataSource="${db}" var="table">
                SELECT * FROM user WHERE id=${sessionScope.login};
            </sql:query>


            <c:forEach var="user" items="${table.rows}">
            <sql:query dataSource="${db}" var="table2">
                SELECT name FROM type_user WHERE id=${user.id_type_user};
            </sql:query>
            <ul class="list-group">
                <li class="list-group-item active"> Użytkownik: ${user.username}
                </li>
                <li class="list-group-item">E-mail: ${user.email}
                </li>
                <li class="list-group-item">Hasło: ${user.password}
                </li>
                <li class="list-group-item">Typ konta: <c:out value="${table2.rowsByIndex[0][0]}"/>
                </li>
            </ul>
        </div>
        </c:forEach>

        <%@ include file="include/panel.jsp" %>

    </div>
</div>

<%@ include file="include/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

