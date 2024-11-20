<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600&display=swap" rel="stylesheet">
    <title>Storify - Product List</title>
</head>
<body>
    <!-- Navigation Bar -->
    <ul>
        <li><a class="active" href="dashboard.jsp"><img src="resource/logo.png" height="20px"></a></li>
        <li><a href="Stock.jsp">Stock</a></li>
        <li><a href="Profile.jsp">Profile</a></li>
    </ul>

    <!-- Header Section -->
    <header>
        <h1>Welcome to Storify - Product List</h1>
    </header>

    <!-- Main Content -->
    <main>
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Last Updated By</th>
                </tr>
            </thead>
            <tbody>
                <%
                // Database Connection and Query Execution
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/storify"; // Adjust DB name if necessary
                    String username = "root"; // Adjust username if different
                    String password = ""; // Adjust password if different
                    String query = "SELECT * FROM products";

                    Connection conn = DriverManager.getConnection(url, username, password);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ProductId") %></td>
                    <td><%= rs.getString("ProductName") %></td>
                    <td><%= rs.getInt("ProductQuantity") %></td>
                    <td><%= rs.getDouble("ProductPrice") %></td>
                    <td><%= rs.getString("last_updated_by") != null ? rs.getString("last_updated_by") : "N/A" %></td>
                </tr>
                <%
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<tr><td colspan='5'>Error retrieving products.</td></tr>");
                }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>