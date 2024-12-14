<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
</head>
<body>
<h1>Create a New Product</h1>
<form method="post" action="/products/create">
    <label for="name">Product Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="color">Color:</label>
    <select id="color" name="color" required>
        <option value="RED">Red</option>
        <option value="BLUE">Blue</option>
        <option value="BLACK">Black</option>
        <option value="WHITE">White</option>
    </select><br><br>

    <label for="size">Size:</label>
    <select id="size" name="size" required>
        <option value="SIZE_40">Size 40</option>
        <option value="SIZE_41">Size 41</option>
        <option value="SIZE_42">Size 42</option>
        <option value="SIZE_43">Size 43</option>
    </select><br><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" step="0.01" required><br><br>

    <label for="amount">Amount:</label>
    <input type="number" id="amount" name="amount" required><br><br>

    <button type="submit">Create Product</button>
</form>
<a href="/products/all">Back to Product List</a>
</body>
</html>
