package hf.keymaster.application;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.user.User;
import hf.keymaster.user.UserDAO;
import hf.keymaster.utils.Validators;

public class CreateApplicationServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/createapp.jsp");
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String App_Name = request.getParameter("name");
		String App_Description = request.getParameter("description");
		String App_Website = request.getParameter("website");
		String App_Version = request.getParameter("version");
		HttpSession session = request.getSession();	
		
		User _u = (User) session.getAttribute("user");
		
		if(_u != null)
		{
			if(_u.isDeveloper())
			{
				if(ApplicationDAO.createApplication(_u, App_Name, App_Description, App_Website, Integer.parseInt(App_Version)))
				{
				/*TODO Successo*/}
			}
		}
	}
}
