package hf.keymaster.license;

import java.io.IOException;

import  jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import hf.keymaster.application.Application;
import hf.keymaster.user.User;
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;

@WebServlet(name = "CreateLicenseServlet", displayName = "CreateLicenseServlet", urlPatterns = {
		"/app/manage/licenses/new" })
public class CreateLicenseServlet extends HttpServlet {
	private static final long serialVersionUID = -1278934119577760266L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/app/list");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String License_Name = request.getParameter("name");
		String License_Description = request.getParameter("description");
		String License_Duration = request.getParameter("duration");
		String License_Type = request.getParameter("type");
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/newlicense.jsp");

		User _u = (User) session.getAttribute("user");
		Application _a = (Application) session.getAttribute("app");

		if (_u != null && _a != null) {
			if (_u.isDeveloper() && _u.getID() == _a.getOwnerID()) {
				if (!(License_Name == null || License_Description == null)
						&& !(License_Duration == null || License_Type == null)) {
					if (LicenseDAO.createLicense(_a, License_Name, License_Description,
							Integer.parseInt(License_Duration), Integer.parseInt(License_Type))) {
						Utils.setAlert(new Alert("License created successfully.", "success"), session);
						response.sendRedirect("/app/manage/licenses/list");
					} else {
						Utils.setAlert(new Alert("Cannot create license, please check fileds.", "danger"), session);
					}
				}
				req.include(request, response);
			}
		}
	}
}
