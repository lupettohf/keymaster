package hf.keymaster.application;

import java.awt.print.Book;
import java.io.IOException;

import hf.keymaster.user.User;
import hf.keymaster.user.UserDAO;
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "RevokeApplicationServlet", displayName = "RevokeApplicationServlet", urlPatterns = {
"/app/manage/revoke" })
public class RevokeApplicationServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
HttpSession session = request.getSession();
RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/revokeapp.jsp");

User _u = (User) session.getAttribute("user");

if (_u != null) {
	if (!_u.isDeveloper()) {
		response.sendRedirect("/user");
	} else {
		String ApplicationID = request.getParameter("id");
		if (ApplicationID.isEmpty()) {
			response.sendRedirect("/app/manage");
		}
		Application _a = ApplicationDAO.getApplication(Integer.parseInt(ApplicationID));

		if (_a.getOwnerID() != _u.getID()) {
			response.sendRedirect("/app/manage");
		} else {
			
		}
	}
} else {
	response.sendRedirect("/login");
}

req.include(request, response);
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
HttpSession session = request.getSession();
RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/revokeapp.jsp");
String ApplicationID = request.getParameter("manage");
String ApplicationName = request.getParameter("name");
String UserPassword = request.getParameter("userpass");
String UpdateDetails = request.getParameter("revoke");

User _u = (User) session.getAttribute("user");
Application _a = null; // Current Application

if (ApplicationID.isEmpty()) {
	response.sendRedirect("/app/list");
}
if (!_u.isDeveloper()) {
	response.sendRedirect("/user");
}

try {
	_a = ApplicationDAO.getApplication(Integer.parseInt(ApplicationID));

	if (_a.getOwnerID() != _u.getID()) {
		response.sendRedirect("/app/list");
	
	} else {
		session.removeAttribute("app");
		session.setAttribute("app", _a);
		Boolean execute = true;
		if(UserDAO.loginUser(_u.getUsername(), UserPassword) == -1)
		{
			execute = false;
			Utils.setAlert(new Alert("The provided password is incorrect.", "danger"), session);
		}
		if(!_a.getName().contains(ApplicationName))
		{
			execute = false;
			Utils.setAlert(new Alert("The application name provided is incorrect.", "danger"), session);
		}
		
		if (UpdateDetails != null && execute) {
			ApplicationDAO.revokeApplication(_a);
			Utils.setAlert(new Alert("Application " +_a.getName() + " successfully revoked." , "success"), session);
		}
	}
} catch (Exception e) {
	e.printStackTrace();
}
req.include(request, response);
}
}