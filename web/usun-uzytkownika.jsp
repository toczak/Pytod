<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 08.12.2019
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Usun uzytkownika</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.type != 3}">
        <c:redirect url="index"/>
    </c:when>
    <c:otherwise>
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/pytod" user="root"
                           password=""/>

        <sql:update dataSource="${db}" var="data">
        DELETE FROM grade_answer
        WHERE id_user='${param.id_user}'
        </sql:update>


        <sql:query dataSource="${db}" var="post2">
            SELECT id FROM post WHERE id_user=${param.id_user};
        </sql:query>
        <c:forEach var="post" items="${post2.rows}">
            <sql:query dataSource="${db}" var="post">
                SELECT id FROM answer_post WHERE id_post
                =${post.id};
            </sql:query>
            <c:forEach var="answer" items="${post.rows}">
                <%--<c:out value="${answer.id}"/>--%>
                <sql:update dataSource="${db}" var="data">
                    DELETE FROM grade_answer
                    WHERE id_answer_post='${answer.id}'
                </sql:update>
            </c:forEach>
        </c:forEach>
        <%--<sql:query dataSource="${db}" var="post">--%>
            <%--SELECT id FROM answer_post WHERE id_user=${param.id_user};--%>
        <%--</sql:query>--%>
        <%--<c:forEach var="answer" items="${post.rows}">--%>
            <%--&lt;%&ndash;<c:out value="${answer.id}"/>&ndash;%&gt;--%>
            <%--<sql:update dataSource="${db}" var="data">--%>
                <%--DELETE FROM grade_answer--%>
                <%--WHERE id_answer_post='${answer.id}'--%>
            <%--</sql:update>--%>
        <%--</c:forEach>--%>

        <sql:update dataSource="${db}" var="data">
            DELETE FROM answer_post
            WHERE id_user='${param.id_user}'
        </sql:update>

 <%--odta--%>

        <sql:query dataSource="${db}" var="post">
            SELECT id FROM post WHERE id_user=${param.id_user};
        </sql:query>
        <c:forEach var="answer" items="${post.rows}">
            <sql:update dataSource="${db}" var="data">
                DELETE FROM answer_post
                WHERE id_post='${answer.id}'
            </sql:update>
        </c:forEach>

        <sql:update dataSource="${db}" var="data">
            DELETE FROM post
            WHERE id_user='${param.id_user}'
        </sql:update>

        <sql:update dataSource="${db}" var="data">
            DELETE FROM user
            WHERE id='${param.id_user}'
        </sql:update>

        <c:redirect url="lista-uzytkownikow.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>