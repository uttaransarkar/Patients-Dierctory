<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Patients directory</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<div id="wrapper">
	<div id="header">
		<h2>Patients Directory</h2>
	</div>
</div>
	
	
	
	<div id="container">
		<div id="content">
		
			<!-- opens up the add patient jsp page -->
			<input type="button" value="ADD PATIENT" 
				class="add-student-button" 
				onclick="window.location.href='patient-add-form.jsp'; return false"></input>
			
			<table>
				<tr>
					<th>Time</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Gender</th>
					<th>New/Update Prescription</th>
					<th>Status</th>
				</tr>
				
				<c:forEach var="thisPatient" items="${list_patients}">
				
				<!-- link for viewing and updating the prescription -->
				<c:url var="updatePres" value="ControllerPatientServlet">
					<c:param name="command" value="LOAD"></c:param>
					<c:param name="patientId" value="${thisPatient.id}"></c:param>
				</c:url>
				
				<tr>
					<td>${thisPatient.time}</td>
					<td>${thisPatient.firstName}</td>
					<td>${thisPatient.lastName}</td>
					<td>${thisPatient.age}</td>
					<td>${thisPatient.gender}</td>
					<td><a href="${updatePres}">GO</a></td>
					<td>${thisPatient.status}</td>
				</tr>
				
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>