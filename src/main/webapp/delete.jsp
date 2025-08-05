<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="in.rs.model.User" %>
<%@ page import="in.rs.model.Registration" %>
<%@ page session="true" %>

<%
    String uname = (String) session.getAttribute("uname");
    if (uname == null || !uname.equalsIgnoreCase("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }

    Registration dao = new Registration(session);
    ArrayList<User> users = dao.getAllUsers();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Delete Users</title>
    <style>
        body { font-family: Arial; background: #f4f4f4; }
        .container {
            width: 700px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px gray;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }
        .btn-delete {
            background: #dc3545;
            color: white;
            padding: 6px 12px;
            text-decoration: none;
            border-radius: 5px;
        }
        .msg {
            text-align: center;
            color: green;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Admin: Delete Users</h2>

    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
        <div class="msg"><%= msg %></div>
    <% } %>

    <table>
        <tr>
            <th>Sl. No</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
        <% for (User u : users) { %>
            <tr>
                <td><%= u.getId() %></td>
                <td><%= u.getName() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getPhone() %></td>
                <td><%= u.getDate() %></td>
                <td>
                    <a href="delete?id=<%= u.getId() %>" class="btn-delete"
                       onclick="return confirm('Are you sure to delete this user?');">Delete</a>
                </td>
            </tr>
        <% } %>
    </table>
</div>
</body>
</html>
