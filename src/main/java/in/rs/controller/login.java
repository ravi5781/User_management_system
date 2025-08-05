package in.rs.controller;

import java.io.IOException;

import in.rs.model.Registration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession();
		Registration dao=new Registration(session);
		
		String result=dao.login(email, password);
		
		if(result.equals("success")) {
			response.sendRedirect("index.jsp");
		}
		else {
			request.setAttribute("msg", "Invalid Email or Password!!");
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
}
