<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Аккаунт</title>
</head>
<body>
    <h3>Ваши данные:</h3>
    <table>
        <tr>
            <td>Логин: </td>
            <td>${user.username}</td>
        </tr>
        <tr>
            <td>Имя: </td>
            <td>${user.firstName}</td>
        </tr>
        <tr>
            <td>Фамилия: </td>
            <td>${user.secondName}</td>
        </tr>
        <tr>
            <td>Email: </td>
            <td>${user.email}</td>
        </tr>
    </table>
    <a href="/logout"><button>Выйти</button></a>
</body>
</html>
