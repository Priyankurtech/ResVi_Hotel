<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/studentlogin.css" />
</head>
<body>
<h2 style="color: green;">${pass}</h2>
  <h2 style="color: red;">${fail}</h2>
<h1>Welcome to Student Login Page</h1>
    <div id="login">
        <form action="" method="post">
            <table>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" id="password" name="password"></td>
                </tr>
                <tr>
                    <td><button type="submit">Login</button></td>
                    <td><button type="reset">Cancel</button></td>
                </tr>
            </table>
        </form>
        <a href="#">Forgot Password?</a> <br>
        <a href="/student/signup"><button>Create New Account</button></a> <br> <br>
        <a href="/"><button>Back</button></a>
    </div>
</body>
</html>