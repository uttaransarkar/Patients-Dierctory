<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Add Patient</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>

<div id="wrapper">
	<div id="header">
		<h2>Patients Directory</h2>
	</div>
</div>

<div id="container">
	<h3>Add Patient</h3>

	<form action="ControllerPatientServlet" method="POST">
		
		<input type="hidden" name="command" value="ADD">
		<table>
			<tbody>
				<tr>
					<td><input type="text" name="firstName" placeholder="First Name" required="required">
				</tr>
				<tr>
					<td><input type="text" name="lastName" placeholder="Last Name" required="required">
				</tr>
				<tr>
					<td><input type="text" name="age" placeholder="Age" required="required">
				</tr>
				<tr>
				
					<td>Gender: <input type="radio" name="gender" value="Male" class="gen" required>Male
                    <input type="radio" name="gender" value="Female" class="gen" required>Female
                    <input type="radio" name="gender" value="Special" class="gen" required>Secret</td>
				
				</tr>
				<tr>
					<td><label></label></td>
						<td><input type="submit" value="ADD" class="save" />
					</td>
				</tr>			
			</tbody>
		</table>
		
	</form>
	
	<div style="clear: both;"></div>
	<p>
		<a href="ControllerPatientServlet">View list</a>
	</p>
	
</div>
</body>
</html>