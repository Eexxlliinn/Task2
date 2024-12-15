<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Cart</title>
</head>
<body>
<h1>Update Cart</h1>
<form method="post" action="/cart/update">
    <input type="hidden" name="cartId" value="${cart.cartId}">
    <label for="userId">User ID:</label>
    <input type="number" id="userId" name="userId" value="${cart.userId}" required>
    <br><br>
    <label for="currency">Currency:</label>
    <input type="text" id="currency" name="currency" required>
    <br><br>
    <button type="submit">Update Cart</button>
</form>
<br>
<a href="/cart/details?cartId=${cart.cartId}">Back to Cart Details</a>
</body>
</html>
