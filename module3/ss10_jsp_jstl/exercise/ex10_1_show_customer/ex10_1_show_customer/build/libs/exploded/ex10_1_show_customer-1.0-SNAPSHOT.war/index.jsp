<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 1/8/2024
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
    <link rel="stylesheet" href="/bootstrap-5.2.3-dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container-fluid">
    <div class="calculator-price d-flex">
        <form method="post" name="calculator">
            <div><label>Product Description</label></div>
            <div><input type="number" name="product" id="product"></div>
            <div><label>List Price</label></div>
            <div><input type="number" name="price" id="price"></div>
            <div><label>Discount Percent (%)</label></div>
            <div><input type="number" name="discount" id="discount"></div>
            <br>
            <div>
                <button type="submit">Calculate now</button>
            </div>
        </form>
    </div>
</div>
</body>
<script src="/bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>
</html>
