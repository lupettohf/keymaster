package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		String User = (String) session.getAttribute("User");
		
		if(User == null)
		{
			 req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
		} else {
			 req = request.getRequestDispatcher("/skeletons/pages/user.jsp");
		}		
		req.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		
		if(!(Username.isEmpty() || Password.isBlank()))
		{
			int ID = UserDAO.loginUser(Username, Password);
			if(ID != -1)
			{
				session.setAttribute("User", ID);
				response.sendRedirect("user");
			} else {
				response.sendRedirect("login");
				//TODO Mostrare Alert Errore
			}
		}
		
	}

}
