<%@ page import="Model.Post" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 23.11.2019
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<div class="col-md-4">

    <div class="card my-4">
        <h5 class="card-header">Opcje</h5>
        <div class="card-body">
            <c:if test="${sessionScope.login!=null}">
                <a class="btn btn-success btn-block" href="zadaj.jsp">Zadaj pytanie</a>
                <br/>
            </c:if>
            <div class="alert alert-dark text-center" role="alert">
                Zalogowani użytkownicy:
                <span class="badge badge-light">
                    ${applicationScope['count']}
                </span>
            </div>
        </div>
    </div>
    <%--<%--%>
        <%--try {--%>
            <%--Class.forName("com.mysql.jdbc.Driver");--%>
        <%--} catch (ClassNotFoundException e) {--%>
            <%--e.printStackTrace();--%>
        <%--}--%>
    <%--%>--%>

    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/pytod" user="root" password=""/>
    <sql:query dataSource="${db}" var="post">
        SELECT * FROM post ORDER BY id DESC LIMIT ${initParam.MaxNewPost};
    </sql:query>

    <c:forEach var="post" items="${post.rows}">
        <div class="list-group">
            <a href="pytanie?id=${post.id}"
               class="list-group-item list-group-item-action flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">Pytanie ${post.id}
                    </h5>
                    <small class="text-muted">
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${post.date}" />
                    </small>
                </div>
                <p class="mb-1">${post.text}
                </p>
                <p class="float-right btn btn-primary btn-sm mb-0">Przejdź do pytania &rarr;</p>
            </a>
        </div>
    </c:forEach>

    <%--<%--%>
        <%--List<Post> newPostList = JSONPost.getNewPostList();--%>
        <%--for (Post post : newPostList) {--%>
    <%--%>--%>

    <%--<div class="list-group">--%>
        <%--<a href="pytanie?id=<%=post.getId()%>"--%>
           <%--class="list-group-item list-group-item-action flex-column align-items-start">--%>
            <%--<div class="d-flex w-100 justify-content-between">--%>
                <%--<h5 class="mb-1">Pytanie <%=post.getId()%>--%>
                <%--</h5>--%>
                <%--<small class="text-muted"><%=post.getDate()%>--%>
                <%--</small>--%>
            <%--</div>--%>
            <%--<p class="mb-1"><%=post.getTextQuestion()%>--%>
            <%--</p>--%>
            <%--<p class="float-right btn btn-primary btn-sm mb-0">Przejdź do pytania &rarr;</p>--%>
        <%--</a>--%>
    <%--</div>--%>
    <%--<%--%>
        <%--}--%>
    <%--%>--%>

    <%@ include file="search-widget.jsp" %>

</div>


</html>
