<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Patient Update Form</title>
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
	<h3>Update Patient Info</h3>
	
	<form action="ControllerPatientServlet" method="GET">
		
		<input type="hidden" name="command" value="UPDATE">
		<input type="hidden" name="patientId" value="${loaded_patient.id}">
		<table>
			<tbody>
				<tr>
					<td>First Name: <input type="text" name="firstName" value="${loaded_patient.firstName}"></td>
				</tr>
				<tr>
					<td>Last Name: <input type="text" name="lastName" value="${loaded_patient.lastName}"></td>
				</tr>
				<tr>
					<td>Age:     <input type="text" name="age" value="${loaded_patient.age}"></td>
				</tr>
				<tr>
					<td>Gender:  <input type="text" name="gender" value="${loaded_patient.gender}"></td>
				</tr>
				<tr>
					<td>Prescription: <input type="text" name="pres" value="${loaded_patient.pres}"></td>
				</tr>
				<tr>
					<td>Status: <input type="text" name="status" value="${loaded_patient.status}"></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="UPDATE" class="save" />
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