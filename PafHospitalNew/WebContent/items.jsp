
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Hospital"%>

<!DOCTYPE htmlt>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospitals Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">


				<h1>Hospitals Management</h1>

				<form id="formHospital" name="formHospital" method="post" action="items.jsp">
					Hospital name : <input id="hospital_name" name="hospital_name" type="text"
						class="form-control form-control-sm"> <br> 
					Hospital Address No. : <input id="hospital_address_no" name="hospital_address_no" type="text"
						class="form-control form-control-sm"> <br> 
					Hospital Address Lane 1 : <input id="hospital_address_lane1" name="hospital_address_lane1" type="text"
						class="form-control form-control-sm"> <br>
					Hospital Address Lane 2 : <input id="hospital_address_lane2" name="hospital_address_lane2" type="text"
						class="form-control form-control-sm"> <br>
					Hospital Address Lane 3 : <input id="hospital_address_lane3" name="hospital_address_lane3" type="text"
						class="form-control form-control-sm"> <br>
					City : <input id="hospital_city" name="hospital_city" type="text"
						class="form-control form-control-sm"> <br>
					Tel : <input id="Tel" name="Tel" type="text"
						class="form-control form-control-sm"> <br>
					Email : <input id="Email" name="Email" type="text"
						class="form-control form-control-sm"> <br>
					
					 <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidItemIDSave" name="hidItemIDS ave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divHospitalsGrid">
					<%
						Hospital hosObj = new Hospital();
					out.print(hosObj.readHospital());
					%>
				</div>
</body>
</html>