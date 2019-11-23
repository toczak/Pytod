<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 22.11.2019
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top mb-4">
    <div class="container">
        <a class="navbar-brand" href="index"><img src="res/logopytod.png" height="60px" alt="Pytod - pytaj i odpowiadj"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse justify-content-between align-items-center w-100" id="collapsingNavbar2">
            <ul class="navbar-nav mx-auto mt-3">
                <li class="nav-item">
                    <p class="btn btn-primary btn-sm mr-3">
                        <small>Łączna liczba pytań: <span class="badge badge-light">4</span></small>
                    </p>
                </li>
                <li class="nav-item">
                    <p class="btn btn-primary btn-sm">
                        <small>Łączna liczba odpowiedzi: <span class="badge badge-light">4</span></small>
                    </p>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index">Strona główna
                    </a>
                </li>

                <c:choose>
                    <c:when test="${sessionScope.login==null}">
                        <a class="nav-link" href="zaloguj.jsp">Zaloguj się</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-secondary" href="wyloguj">Wyloguj</a>
                    </c:otherwise>
                </c:choose>

                <c:if test="${sessionScope.login==null}">
                    <a class="nav-link" href="rejestracja.jsp">Zarejestruj się</a>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</html>