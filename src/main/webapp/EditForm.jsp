<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="in.rs.model.User" %>

<%
    String uname = (String) session.getAttribute("uname");
    if (uname == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f0f0;
        }
        .container {
            width: 450px;
            margin: 60px auto;
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
        input[type=text], input[type=email] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid gray;
        }
        input[type=submit] {
            width: 100%;
            background: #28a745;
            color: white;
            padding: 10px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .message {
            color: green;
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
    <h2>Edit Profile</h2>

    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
        <div class="message"><%= msg %></div>
    <% } %>

    <form action="register" method="post">
        <input type="hidden" name="action" value="edit" />

        <div class="form-control">
            <input type="text" name="name" value="<%= user.getName() %>" required>
        </div>
        <div class="form-control">
            <input type="text" name="phone" value="<%= user.getPhone() %>" required>
        </div>
        <div class="form-control">
            <input type="email" name="email" value="<%= user.getEmail() %>" required>
        </div>
        <div class="form-control">
            <input type="submit" value="Update">
        </div>
    </form>

    <div class="link">
        <a href="index.jsp">Back to Home</a>
    </div>
</div>

</body>
</html>
