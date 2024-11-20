<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
<form action=RegisterServlet method=post>
	<table>
		<tr><td>Enter Username: <input type=text name=txtUsername> </td></tr>
		<tr><td>Enter Full Name: <input type=text name=txtFullName> </td></tr>
		<tr><td>Enter Email : <input type=text name=txtEmail> </td></tr>
		<tr><td>Enter Address : <input type=text name=txtAddress> </td></tr>
		<tr>
			<td>
				Enter Date of Birth: 
				<select name="dobDay">	
					<option value="" disabled selected>Day</option>
					<% for (int i = 1; i <= 31; i++) { %>
						<option value="<%= i %>"><%= i %></option>
					<% } %>
				</select>
				<select name="dobMonth">
					<option value="" disabled selected>Month</option>
					<option value="1">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">August</option>
					<option value="9">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
				</select>
				<select name="dobYear">
					<option value="" disabled selected>Year</option>
					<% for (int i = 1900; i <= 2024; i++) { %>
						<option value="<%= i %>"><%= i %></option>
					<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				Select Gender:
				<select name="gender">
				<option value="" disabled selected>Gender</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>
				</select>
			</td>
		</tr>
		<tr><td>Enter Password: <input type=password name=txtPassword> </td></tr>
		<tr><td>Confirm Password: <input type=password name=txtConfirmPassword> </td></tr>
		<tr>
			<td><input type=submit value=Register></td>
			<td><input type=reset></td></tr>
	</table>	
</form>
<p>If you already have an account, click <a href="index.jsp">here</a> to login.</p>
</body>
</html>