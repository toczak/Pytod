<%@ page import="Model.AnswerPost" %>
<%@ page import="JSON.JSONAnswerPost" %>
<%@ page import="JSON.JSONGradeAnswer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <style>
    .red-text {
    color: black;
    }

    @media (max-width: 600px) {

    .red-text {
    color:red;
    }

    }
    </style>
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
                        <p class="card-text col-9 red-text">${question}</p>
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
            <%
                JSONGradeAnswer.setRealPath(request.getServletContext().getRealPath(""));
                List<AnswerPost> answerPostList = JSONAnswerPost.getAnswerPostListWithPostId((int) request.getAttribute("id"));
                for (AnswerPost answer : answerPostList) {
            %>
            <div class="card mb-4">
                <div class="card-header">
                    Użytkownik <b><%=answer.getAuthor()%>
                </b> napisał dnia <i><%=answer.getDate()%>
                </i>:
                </div>
                <div class="card-body">
                    <p class="card-text"><%=answer.getText()%>
                    </p>
                </div>
                <div class="card-footer text-muted p-0">
                    <div class="d-flex m-1">
                        <%
                            if (session.getAttribute("login") == null || answer.getAuthor().equals(session.getAttribute("login")) ||
                               JSONGradeAnswer.checkGradeAnswerOfUser(answer.getId(),(String) session.getAttribute("login"))) {
                        %>
                        <small class="text-muted mr-auto ml-3">Ocena:
                            <b><%=JSONGradeAnswer.getAverageOfAnswer(answer.getId())%>
                            </b></small>

                        <% } else {%>
                        <m:GradeAnswer idPost='<%=(int) request.getAttribute("id")%>' idAnswer="<%=answer.getId()%>"/>
                        <%}%>
                    </div>
                </div>
            </div>
            <%}%>

            <%--<c:forEach items="${answerList}" var="list">--%>
            <%--<div class="card mb-4">--%>
            <%--<div class="card-header">--%>
            <%--Użytkownik <b>${list.author}</b> napisał dnia <i>${list.date}</i>:--%>
            <%--</div>--%>
            <%--<div class="card-body">--%>
            <%--<p class="card-text">${list.text}</p>--%>
            <%--</div>--%>
            <%--<div class="card-footer text-muted p-0">--%>
            <%--<div class="d-flex m-1">--%>
            <%--<c:choose>--%>
            <%--<c:when test="${sessionScope.login==null || list.author==sessionScope.login}">--%>
            <%--<small class="text-muted mr-auto ml-3">Ocena: <b><%= JSONGradeAnswer.getAverageOfAnswer(${list.}(int) request.getAttribute("id"))%></b></small>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<m:GradeAnswer idAnswer="${list.id}" idPost="${id}"/>--%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>

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
