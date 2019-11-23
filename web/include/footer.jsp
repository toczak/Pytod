<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 23.11.2019
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<footer class="py-2 bg-dark mt-3">
    <div class="container">
        <c:if test="${sessionScope.login!=null}">
            <div class="d-flex justify-content-center">
                <a class="mr-4" href="http://webenlance.com">Moje konto</a>
                <a class="mr-4" href="http://webenlance.com">Moje pytania</a>
                <a href="http://webenlance.com">Moje odpowiedzi</a>
            </div>
            <hr/>
        </c:if>

        <p class="m-0 text-center text-white">Copyright &copy; Patryk Potoczak 2019</p>
    </div>
</footer>
</html>
