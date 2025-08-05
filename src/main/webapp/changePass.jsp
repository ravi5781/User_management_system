<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
        }
        .container {
            width: 400px;
            margin: 70px auto;
            background: white;
            padding: 25px;
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
            background: #dc3545;
            color: white;
            padding: 10px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
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
    <h2>Reset Password</h2>

    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
        <div class="message"><%= msg %></div>
    <% } %>

    <form action="resetPassword" method="post">
        <div class="form-control">
            <input type="email" name="email" placeholder="Enter Email" required>
        </div>
        <div class="form-control">
            <input type="password" name="oldpass" placeholder="Enter Old Password" required>
        </div>
        <div class="form-control">
            <input type="password" name="newpass" placeholder="Enter New Password" required>
        </div>
        <div class="form-control">
            <input type="submit" value="Reset Password">
        </div>
    </form>

    <div class="link">
        <a href="login.jsp">Back to Login</a>
    </div>
</div>

</body>
</html>
