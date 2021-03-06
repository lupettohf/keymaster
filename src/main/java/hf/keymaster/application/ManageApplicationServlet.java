package hf.keymaster.application;

import java.io.IOException;
import java.util.List;

import  jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.key.Key;
import hf.keymaster.license.owned.OwnedLicenseDAO;
import hf.keymaster.user.User;
import hf.keymaster.user.UserDAO;
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;

@WebServlet(name = "ManageApplicationServlet", displayName = "ManageApplicationServlet", urlPatterns = {
		"/app/manage" })
public class ManageApplicationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/manageapp.jsp");

		User _u = (User) session.getAttribute("user");
		if (_u != null) {
			if (!_u.isDeveloper()) {
				response.sendRedirect("/user");
			} else {
				response.sendRedirect("/app/list");
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
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/manageapp.jsp");
		String ApplicationID = request.getParameter("manage");
		String ApplicationName = request.getParameter("name");
		String ApplicationDescription = request.getParameter("description");
		String ApplicationWebsite = request.getParameter("website");
		String ApplicationVersion = request.getParameter("version");
		String RegenerateApikey = request.getParameter("regenerate_api");
		String UpdateDetails = request.getParameter("update_details");
		String AddUser = request.getParameter("add_user");
		String License = request.getParameter("license");
		String TargetUser = request.getParameter("tgtuser");
		User _u = (User) session.getAttribute("user");
		Application _a = null; // Current Application
		Application _na = null; // New Application
		
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
				List<License> _l = LicenseDAO.getLicenses(_a);
				session.removeAttribute("licenses");
				session.setAttribute("licenses", _l);
				if (RegenerateApikey != null) {
					ApplicationDAO.regenerateAPIKey(_a);
					response.sendRedirect("/app/manage");
				}
				if(AddUser != null)
				{
					try {
						User _tgtUser = (User) UserDAO.getUserByName(TargetUser);
						License _License = (License) LicenseDAO.getLicense(Integer.parseInt(License));
						if(_tgtUser != null)
						{
							if(OwnedLicenseDAO.activateLicense(_tgtUser, _License, new Key(-1, _License.getID(), "manual_upgrade", true)))
								{
									Utils.setAlert(new Alert("User " + _tgtUser.getUsername() + " has been upgraded to " + _License.getName(), "success"), session);
								} else {
									Utils.setAlert(new Alert("Cannot upgrade user " + _tgtUser.getUsername() + "to plan " + _License.getName(), "danger"), session);
								}
							
						}
					}catch(Exception e) {
						Utils.setAlert(new Alert("User " + TargetUser.toString() + " is not registred in our system.", "danger"), session);
					}
					
				}
				if (UpdateDetails != null) {
					_na = _a;
					_na.setName(ApplicationName);
					_na.setDescription(ApplicationDescription);
					_na.setWebsite(ApplicationWebsite);
					_na.setVersion(Integer.parseInt(ApplicationVersion));
					ApplicationDAO.updateApplication(_a, _na);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.include(request, response);
	}
}
