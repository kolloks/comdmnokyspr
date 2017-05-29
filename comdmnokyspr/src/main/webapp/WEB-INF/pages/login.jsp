<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ru">
<head>
    <title>Логин</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container">
    <form method="POST" action="<c:url value="/j_spring_security_check"/>" class="form-signin">
        <h2 class="form-heading">Вход в систему</h2>
            <input name="j_username" type="text" class="form-control" placeholder="Логин"
                   autofocus="true"/>
            <input name="j_password" type="password" class="form-control" placeholder="Пароль"/>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            <h4 class="text-center"><a href="/registration">Регистрация</a></h4>
            <span><c:out value="${message}"/></span>
    </form>
</div>
</body>
</html>
