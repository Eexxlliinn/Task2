<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Create Cart</title>
</head>
<body>
<h1>Create a New Cart</h1>
<form method="post" action="/cart/create">
  <label for="userId">User ID:</label>
  <input type="number" id="userId" name="userId" required>
  <br><br>
  <label for="currency">Currency:</label>
  <input type="text" id="currency" name="currency" required>
  <br><br>
  <button type="submit">Create Cart</button>
</form>
<br>
<a href="/products/all">Back to Product List</a>
</body>
</html>
