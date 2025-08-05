<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Invalidate session
    session.invalidate();

    // Redirect to login page
    response.sendRedirect("login.jsp");
%>
