<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h2 style="color: green;">${pass}</h2>
  <h2 style="color: red;">${fail}</h2>
   <form action="/student/verify-otp" method="post">
	<input type="text" name="id" value="${id}" hidden="hidden">
	Enter OTP:<input type="number" name="otp">
	<button>Submit</button>
		<button type="reset">Cancel</button>
	</form>
	<br>
	<a href="/student/resend-otp/${id}"><button>Resend Otp</button></a>
</body>
</html>