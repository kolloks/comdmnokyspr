<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Продукт ${product.name}</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <p>Текущий продукт: ${product.name}, цена ${product.price}</p>
    <br><a href="/product/add/${product.id}" ><button>Добавить в корзину</button></a>
    <h3>Корзина</h3>
    <c:if test="${sessionScope.get('basket')!=null}">
        <ul>
            <c:forEach var="productInBasket" items="${sessionScope.get('basket')}">
                <li>
                    <a href="/product/${productInBasket.key.id}">
                        ${productInBasket.key.name}</a> : ${productInBasket.value} шт.
                    <a href="/product/${product.id}/empty/${productInBasket.key.id}">Убрать шт.</a>
                </li>
            </c:forEach>
        </ul>
        <p><a href="/product/buy"><button>Купить</button></a></p>
    </c:if>
    <p><a href="/product/list/">Весь список продуктов</a></p>
</body>
</html>
