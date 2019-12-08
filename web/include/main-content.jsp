<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 23.11.2019
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<div class="col-md-8">

    <%--<h1 class="my-4">--%>
    <%--Lista pytań--%>
    <%--</h1>--%>

    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/pytod" user="root" password=""/>


    <c:forEach items="${postList}" var="list">
        <div class="card mb-4">
            <div class="card-body">
                <h2 class="card-title">Pytanie ${list.id}:</h2>
                <p class="card-text">${list.textQuestion}</p>
                <div class="d-flex justify-content-between">
                    <p class="card-text">
                        <sql:query dataSource="${db}" var="post2">
                            SELECT count(id) FROM answer_post WHERE id_post=${list.id};
                        </sql:query>
                        <small class="text-muted">Liczba udzielonych odpowiedzi:
                            <c:out value="${post2.rowsByIndex[0][0]}"/></small>
                    </p>
                    <a href="pytanie?id=${list.id}" class=" btn btn-primary">Przejdź do pytania &rarr;</a>
                </div>
            </div>
            <div class="card-footer">
                <small class="text-muted">Data dodania: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.date}" />
                </small>
            </div>
        </div>
    </c:forEach>

    <nav aria-label="Panel nawigacji">
        <ul class="pagination justify-content-center">
            <li class="page-item <c:if test="${currentPage==1}">disabled</c:if>">

                <a class="page-link" href="index?strona=${currentPage-1}">Poprzednia</a>
            </li>
            <li class="page-item active"><span class="page-link">
                ${currentPage}
            </span></li>
            <%--<li class="page-item"><a class="page-link" href="#">2</a></li>--%>
            <%--<li class="page-item"><a class="page-link" href="#">3</a></li>--%>
            <li class="page-item <c:if test="${currentPage == maxPage}">disabled</c:if>">
                <a class="page-link" href="index?strona=${currentPage+1}">Następna</a>
            </li>
        </ul>
    </nav>

</div>
</html>
