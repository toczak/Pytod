<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 23.11.2019
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="col-md-4">

    <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">Pytanie 343</h5>
                <small class="text-muted">17-11-2019 16:15</small>
            </div>
            <p class="mb-0">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius
                blandit.</p>
            <p class="float-right btn btn-primary btn-sm mb-0">Przejdź do pytania &rarr;</p>
        </a>
        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">Pytanie 343</h5>
                <small class="text-muted">17-11-2019 16:15</small>
            </div>
            <p class="mb-0">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius
                blandit.</p>
            <p class="float-right btn btn-primary btn-sm mb-0">Przejdź do pytania &rarr;</p>
        </a>
        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">Pytanie 343</h5>
                <small class="text-muted">17-11-2019 16:15</small>
            </div>
            <p class="mb-0">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius
                blandit.</p>
            <p class="float-right btn btn-primary btn-sm mb-0">Przejdź do pytania &rarr;</p>
        </a>
    </div>


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
    <%@ include file="search-widget.jsp" %>

</div>


</html>
