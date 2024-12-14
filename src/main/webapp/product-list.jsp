<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Catalog</title>
</head>
<body>
<h1>Product Catalog</h1>
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Color</th>
        <th>Size</th>
        <th>Price</th>
        <th>Amount</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty products}">
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.color}</td>
                <td>${product.size}</td>
                <td>${product.price}</td>
                <td>${product.amount}</td>
                <td>
                    <a href="/products/show?id=${product.id}">View</a>
                    <a href="/products/update?id=${product.id}">Edit</a>
                    <form method="post" action="/products/delete" style="display:inline;">
                        <input type="hidden" name="id" value="${product.id}">
                        <button type="submit">Delete product</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty products}">
        <tr>
            <td colspan="6">No products available.</td>
        </tr>
    </c:if>
    </tbody>
</table>
<br>
<a href="/products/create">Create New Product</a>
</body>
</html>
