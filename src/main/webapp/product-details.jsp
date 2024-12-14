<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
</head>
<body>
<h1>Product Details</h1>
<p>Name: ${product.name}</p>
<p>Color: ${product.color}</p>
<p>Size: ${product.size}</p>
<p>Price: ${product.price}</p>
<p>Amount: ${product.amount}</p>

<a href="/products/all">Back to Product List</a>
<a href="/products/update?id=${product.id}">Edit Product</a>

<form method="post" action="/products/delete">
    <input type="hidden" name="id" value="${product.id}">
    <button type="submit">Delete product</button>
</form>
</body>
</html>
