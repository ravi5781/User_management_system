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


@WebServlet("/resetPassword")
public class resetPassword extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String oldpass=request.getParameter("oldpass");
		String newpass=request.getParameter("newpass");
		
		HttpSession session=request.getSession();
		Registration dao=new Registration(session);
		
		//check old password is correct
		String result=dao.login(email,oldpass);
		
		if(result.equals("success")) {
			result=dao.resetPassword(email, newpass);
			if(result.equals("success")) {
				request.setAttribute("msg", "Password changed successfully!");
			}
			else{
				request.setAttribute("msg", "Password update failed!!");
			}
		}
		else{
			request.setAttribute("msg", "old Password is incorrect!");
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("changePass.jsp");
		rd.forward(request, response);
	}
}
