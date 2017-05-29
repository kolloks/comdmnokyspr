<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Лист продуктов</title>
</head>
<body>
<ul>
    <c:forEach var="list" items="${list}">
        <li>
            <a href="/product/${list.id}">${list.name}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
