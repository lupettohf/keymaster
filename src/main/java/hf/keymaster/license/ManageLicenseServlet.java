package hf.keymaster.license;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.user.User;

@WebServlet(name = "ManageLicenseServlet", displayName="ManageLicenseServlet", urlPatterns = {"/app/manage/licenses/manage"})
public class ManageLicenseServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		HttpSession session = request.getSession();	
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/managealicense.jsp");
		
		User _u = (User) session.getAttribute("user");
		
		if(_u != null) {  
			if(!_u.isDeveloper()) { response.sendRedirect("/user"); }
			else { response.sendRedirect("list"); }
		}else{ response.sendRedirect("/login"); } 
		
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();	
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/managelicense.jsp");
		String LicenseID = request.getParameter("manage");
		String LicenseName = request.getParameter("name");
		String LicenseDescription = request.getParameter("description");
		String LicenseDuration = request.getParameter("duration");
		String LicenseType = request.getParameter("type");
		
		User _u = (User) session.getAttribute("user");
		Application _a = (Application) session.getAttribute("app");
		License _l = LicenseDAO.GetLicense(Integer.parseInt(LicenseID));
		License _nl = null;
		
		if(_l == null) { response.sendRedirect("list"); } 
		if(!_u.isDeveloper()) { response.sendRedirect("/user"); }
		if(_a.getOwnerID() == _u.getID() && _l.getAppID() == _a.getID())
		{
			session.removeAttribute("license");
			session.setAttribute("license", _l);
			if(LicenseName != null && LicenseDescription != null && LicenseDuration != null && LicenseType != null)
			{
				if(LicenseDAO.updateLicense(_l, _nl))
				{
					response.sendRedirect("list");
				} else { 
					//TODO Handle error
				}
			}
		}		
		req.include(request, response);
	}
}
