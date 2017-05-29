<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <c:if test="${username == 'Гость'}">
        ${username} <a href="/login"><button>Войти</button></a>
        <a href="/registration"><button>Регистрация</button></a>
    </c:if>
    <c:if test="${username != 'Гость'}">
        ${username} <a href="/logout"><button>Выйти</button></a>
    </c:if>
</body>
</html>
