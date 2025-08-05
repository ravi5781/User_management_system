<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eef2f3;
        }
        .container {
            width: 400px;
            margin: 80px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px gray;
        }
        h2 {
            text-align: center;
        }
        .form-control {
            margin-bottom: 15px;
        }
        input[type=email], input[type=password] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid gray;
        }
        input[type=submit] {
            width: 100%;
            background: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }
        .message {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
        .link {
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>User Login</h2>

    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
        <div class="message"><%= msg %></div>
    <% } %>

    <form action="login" method="post">
        <div class="form-control">
            <input type="email" name="email" placeholder="Enter Email" required>
        </div>
        <div class="form-control">
            <input type="password" name="password" placeholder="Enter Password" required>
        </div>
        <div class="form-control">
            <input type="submit" value="Login">
        </div>
    </form>

    <div class="link">
        Don't have an account? <a href="Registration.jsp">Register</a><br>
        <a href="changePass.jsp">Forgot Password?</a>
    </div>
</div>
</body>
</html>
