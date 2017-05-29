<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ru">
<head>
    <title>Регистрация</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container">
    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Регистрация аккаунта</h2>
        <spring:bind path="username">
            <div class="${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control"
                            placeholder="Логин" autofocus="true"/>
                <form:errors path="username" delimiter=" "/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="email" path="email" class="form-control"
                            placeholder="eMail"/>
                <form:errors path="email" delimiter=" "/>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="firstName" class="form-control"
                            placeholder="Имя"/>
                <form:errors path="firstName" delimiter=" "/>
            </div>
        </spring:bind>

        <spring:bind path="secondName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="secondName" class="form-control"
                            placeholder="Фамилия"/>
                <form:errors path="secondName" delimiter=" "/>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control"
                            placeholder="Пароль"/>
                <form:errors path="password" delimiter=" "/>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="confirmPassword" class="form-control"
                            placeholder="Повторите пароль"/>
                <form:errors path="confirmPassword" delimiter=" "/>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Отправить</button>
    </form:form>
</div>
</body>
</html>
