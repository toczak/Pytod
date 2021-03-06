<%@ page import="Model.AnswerPost" %>
<%@ page import="JSON.JSONAnswerPost" %>
<%@ page import="JSON.JSONGradeAnswer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/GradeAnswerTag.tld" prefix="m" %>

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
    <script>
        function confirmGo(m, u) {
            if (confirm(m)) {
                window.location = u;
            }
        }
    </script>
    <style>
        .red-text {
            color: black;
        }

        @media (max-width: 600px) {

            .red-text {
                color: red;
            }

        }
    </style>
</head>

<body>

<jsp:include page="include/navbar.jsp"/>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/pytod" user="root"
                   password=""/>
<div class="container mt-2 py-5 mb-3">

    <div class="row">

        <div class="col-md-8">

            <h1>
                Pytanie ${id}:
            </h1>
            <sql:query dataSource="${db}" var="post2">
                SELECT id_user,text,date FROM post WHERE id=${id};
            </sql:query>
            <c:forEach var="post2" items="${post2.rows}">
                <div class="card mb-4 ${color} border-dark">
                    <div class="card-body">
                        <div class="row">
                            <p class="card-text col-9 red-text">${post2.text}</p>
                            <div class="ml-auto col-3 border-left">
                                <p class="ml-auto">Data dodania:
                                    <br/><i><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                            value="${post2.date}"/></i></p>
                                <p class="ml-auto">Użytkownik:<br/>
                                    <sql:query dataSource="${db}" var="post">
                                        SELECT username FROM user WHERE id=${post2.id_user};
                                    </sql:query>
                                    <b><c:out value="${post.rowsByIndex[0][0]}"/></b>
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.type==3}">
                        <div class="card-footer text-muted">
                            Panel administratora: <a
                                href="javascript:confirmGo('Czy na pewno chcesz usunąć pytanie?','usun-pytanie.jsp?id=<c:out value="${id}"/>')">Usuń</a>
                        </div>
                    </c:if>
                </div>
            </c:forEach>

            <sql:query dataSource="${db}" var="table">
                SELECT * FROM answer_post WHERE id_post=${id};
            </sql:query>


            <c:forEach var="answer" items="${table.rows}">
                <sql:query dataSource="${db}" var="table2">
                    SELECT username FROM user WHERE id=${answer.id_user};
                </sql:query>
                <div class="card mb-4">
                    <div class="card-header">
                        Użytkownik <b>
                        <c:forEach var="user" items="${table2.rows}">
                            ${user.username}
                        </c:forEach>
                    </b> napisał dnia <i><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${answer.date}"/></i>:
                    </div>
                    <div class="card-body">
                        <p class="card-text">${answer.text}
                        </p>
                    </div>
                    <div class="card-footer text-muted p-0">
                        <div class="d-flex m-1">

                            <c:if test="${sessionScope.login!=null}">
                                <%-- if he login, check whether graded--%>
                                <sql:query dataSource="${db}" var="userCheck">
                                    SELECT id FROM grade_answer WHERE id_user = ${sessionScope.login} AND id_answer_post = ${answer.id};
                                </sql:query>
                            </c:if>
                            <c:choose>
                                <c:when test="${sessionScope.login==null || answer.id_user==sessionScope.login || userCheck.rowsByIndex[0][0]>0}">
                                    <small class="text-muted mr-auto ml-3">Ocena:
                                        <b>
                                            <sql:query dataSource="${db}" var="table2">
                                                SELECT AVG(grade) FROM grade_answer WHERE id_answer_post = ${answer.id};
                                            </sql:query>
                                            <c:choose>
                                                <c:when test="${not empty table2.rowsByIndex[0][0]}">
                                                    <c:out value="${table2.rowsByIndex[0][0]}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="Brak ocen"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </b></small>
                                </c:when>
                                <c:otherwise>
                                    <m:GradeAnswer idPost='${id}' idAnswer="${answer.id}"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <c:if test="${sessionScope.type==3}">
                        <div class="card-footer text-muted">
                            Panel administratora: <a
                                href="edytuj-odpowiedz.jsp?id_answer=<c:out value="${answer.id}"/>&id_post=<c:out value="${id}"/>">Edytuj</a>
                            <a href="javascript:confirmGo('Czy na pewno chcesz usunąć odpowiedz?','usun-odpowiedz.jsp?id_answer=<c:out value="${answer.id}"/>&id_post=<c:out value="${id}"/>')">Usuń</a>
                        </div>
                    </c:if>
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

</div>
<footer>
    <%@ include file="include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
