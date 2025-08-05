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

@WebServlet("/register")
public class register extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession();
		Registration dao=new Registration(session);
		
		String result=dao.register(name, phone, email, password);
		
		if(result.equals("succuss")) {
			request.setAttribute("msg", "Registered Successfully!");
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		else if(result.equals("existed")) {
			request.setAttribute("msg", "User Alredy Exists!! Try with different phone/email");
			RequestDispatcher rd=request.getRequestDispatcher("Registration.jsp");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("msg", "Registration Failed. Try Again..");
			RequestDispatcher rd=request.getRequestDispatcher("Registration.jsp");
			rd.forward(request, response);
		}
	}
}
