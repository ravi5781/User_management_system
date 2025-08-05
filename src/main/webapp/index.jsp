<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    String uname = (String) session.getAttribute("uname");
    if (uname == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #e8f0fe;
        }
        .container {
            width: 600px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px gray;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        .link {
            margin-top: 20px;
        }
        .link a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            margin: 0 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome, <%= uname %>!</h2>
    <p>You have successfully logged in.</p>

    <div class="link">
        <a href="EditForm.jsp">Edit Profile</a> |
        <a href="changePass.jsp">Change Password</a> |
        <a href="logout.jsp">Logout</a>
    </div>
</div>
</body>
</html>
