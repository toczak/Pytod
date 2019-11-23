<%--
  Created by IntelliJ IDEA.
  User: Patryk
  Date: 16.11.2019
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="card my-4">
    <h5 class="card-header">Znajdź pytanie</h5>
    <div class="card-body">
        <form method="get" action="szukaj">

            <div class="input-group">
                <input type="text" name="fraza" class="form-control" placeholder="Szukana fraza...">
                <span class="input-group-btn">

                <input type="submit" class="btn btn-secondary" value="Szukaj"/>
                </span>
            </div>
            <a class="h6 mt-1" href="wyszukiwanie.jsp">Wyszukiwanie zaawansowane...</a>

        </form>

    </div>
</div>
</html>