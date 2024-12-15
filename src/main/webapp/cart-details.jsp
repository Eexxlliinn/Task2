<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart Details</title>
</head>
<body>
<h1>Cart Details</h1>
<p>Cart ID: ${cart.cartId}</p>
<p>User ID: ${cart.userId}</p>
<h3>Products in Cart:</h3>
<table border="1">
    <thead>
    <tr>
        <th>Product ID</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${cart.cartProducts}">
        <tr>
            <td>${product.productId}</td>
            <td>${product.amount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="/cart/update?cartId=${cart.cartId}">Update Cart</a>
<br>
<form method="post" action="/cart/clear">
    <input type="hidden" name="cartId" value="${cart.cartId}">
    <button type="submit">Clear Cart</button>
</form>
<br>
<a href="/products/all">Back to Product List</a>
</body>
</html>
