<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Главная</title>
  </head>
  <body>
  <p>Привет <jsp:include page="/userAttr"/></p>
  <p>Список продуктов:<br><jsp:include page="/product/list"/></p>
  </body>
</html>
