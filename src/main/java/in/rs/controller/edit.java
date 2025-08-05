package in.rs.controller;

import java.io.IOException;

import in.rs.model.Registration;
import in.rs.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit")
public class edit extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Registration dao=new Registration(session);
		User user=dao.getUserById();
		
		request.setAttribute("user", user);
		RequestDispatcher rd=request.getRequestDispatcher("EditForm.jsp");
		rd.forward(request, response);
		
	}
}
