<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/inputproduct.css">
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600&display=swap" rel="stylesheet">
<title>Update Product</title>
</head>
<body>
	<ul>
        <li><a href="dashboard.jsp"><img src="resource/logo.png" height="20px"></a></li>
        <li><a href="InputProduct.jsp">Input Product</a></li>
        <li><a href="Profile.jsp">Profile</a></li>
    </ul>
    <main>
    	<div class="form-container">
	        <h1>Update Product</h1>
	        <form action="UpdateProductServlet" method="post">
	            <input type="hidden" name="productId" value="${product.ProductId}">
	            <table>
	                <tr>
	                    <td><label for="productName">Product Name</label></td>
	                    <td><input type="text" id="productName" name="productName" value="${product.ProductName}" required></td>
	                </tr>
	                <tr>
	                    <td><label for="productQuantity">Product Quantity</label></td>
	                    <td><input type="number" id="productQuantity" name="productQuantity" value="${product.ProductQuantity}" required></td>
	                </tr>
	                <tr>
	                    <td><label for="productPrice">Product Price</label></td>
	                    <td><input type="number" step="0.01" id="productPrice" name="productPrice" value="${product.ProductPrice}" required></td>
	                </tr>
	                <tr>
	                    <td colspan="2" style="text-align: center;">
	                        <button type="submit">Update</button>
	                    </td>
	                </tr>
	            </table>
	        </form>
	    </div>
    </main>
</body>
</html>
