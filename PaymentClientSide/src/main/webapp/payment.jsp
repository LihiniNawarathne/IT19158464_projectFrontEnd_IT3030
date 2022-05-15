<%@page import="util.DBConnection"%>
<%@page import="recources.PaymentResources"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Payment Management</title>

	<!-- Linking the css scripts -->
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<link rel="stylesheet" href="Views/form.css">
	
	<!-- Linking the js files -->
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/payment.js"></script>

</head>
<body>

	<% DBConnection userConn = new DBConnection(); %>	
	<div class="container">
	<div class="row">
	<div class="col-12">
		<h1 align="center">Payment Management</h1>
		<br>
		<br>
		<center>
		
	<div class="container">
	<div class="row">
	<div class="col-12">	
		<!--------------------- Start of form  ------------------------------->
		<form id="formCon" name="formCon">
		<center><h3>Make a payment</h3></center>
		
			<input id="userID" name="userID" type="text" class="form-control form-control-sm" placeholder="Insert User ID">
			<br> 
		
			<input id="billID" name="billID" type="text" class="form-control form-control-sm" placeholder="billID">
			<br> 
            
            <input id="paid_amount" name="paid_amount" type="text" class="form-control form-control-sm" placeholder="paid_amount">
			<br> 
			
			<input id="payment_type" name="payment_type" type="text" class="form-control form-control-sm" placeholder="payment_type">
			<br> 
			
			<input id="card_no" name="card_no" type="text" class="form-control form-control-sm" placeholder="card_no">
			<br> 
           
			<input id="btnSave" name="btnSave" type="button" value="Add Payment" class="btn btn-primary">
            <input type="hidden" id="hidpaymentIDSave" name="hidpaymentIDSave" value="">
		</form>
		<!--------------------- End of form  ------------------------------->
		
				
		<br>
		<!--------------------- Alerts  ------------------------------->
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		</center>
		
		
		<!--------------------- Display payments  ------------------------------->
		<center>
		<div id="divItemsGrid" >
		<%
			
			PaymentResources paymentObj = new PaymentResources();
			out.print(paymentObj.readPayment());
		%>
		</div>
		</center>

		<br>
		<br>

	</div>
	</div> 
	</div>

</body>
</html>