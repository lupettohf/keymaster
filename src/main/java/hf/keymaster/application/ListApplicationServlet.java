package hf.keymaster.application;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.user.User;

@WebServlet(name = "ListApplicationServlet", displayName="ListApplicationServlet", urlPatterns = {"/app/list"})
public class ListApplicationServlet extends HttpServlet{
	private static final long serialVersionUID = -6146271747637218235L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		HttpSession session = request.getSession();	
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/listapp.jsp");
		
		User _u = (User) session.getAttribute("user");
		
		if(_u != null) {  
			if(!_u.isDeveloper()) { response.sendRedirect("/user"); }
			List<Application> AppList = ApplicationDAO.getApplications(_u);
		
			session.setAttribute("applist", AppList);
		}else{ response.sendRedirect("/login"); } 
		
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
}
