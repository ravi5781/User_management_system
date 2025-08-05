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

@WebServlet("/delete")
public class delete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		HttpSession session = request.getSession();
		Registration dao = new Registration(session);
		
		
		try {
			int id=Integer.parseInt(idStr);
			String result = dao.delete(id);
			
			if(result.equals("success")) {
				request.setAttribute("msg", "User deleted successfully...");
			}
			else {
				request.setAttribute("msg", "User deletion Failed..");
			}
			
		} catch (Exception e) {
			request.setAttribute("msg", "Invalied ID or Error occured..");
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("delete.jsp");
		rd.forward(request, response);
		
	}
}
