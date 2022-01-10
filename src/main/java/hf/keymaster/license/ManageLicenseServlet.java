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

@WebServlet(name = "ManageLicenseServlet", displayName = "ManageLicenseServlet", urlPatterns = {
		"/app/manage/licenses/manage" })
public class ManageLicenseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		User _u = (User) session.getAttribute("user");

		if (_u != null) {
			if (!_u.isDeveloper()) {
				response.sendRedirect("/user");
			} else {
				response.sendRedirect("list");
			}
		} else {
			response.sendRedirect("/login");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		if (_l == null) {
			response.sendRedirect("list");
		}
		if (!_u.isDeveloper()) {
			response.sendRedirect("/user");
		}
		if (_a.getOwnerID() == _u.getID() && _l.getAppID() == _a.getID()) {
			session.removeAttribute("license");
			session.setAttribute("license", _l);
			if (LicenseName != null && LicenseDescription != null && LicenseDuration != null && LicenseType != null) {
				_nl = _l;
				_nl.setName(LicenseName);
				_nl.setDescription(LicenseDescription);
				_nl.setDuration(Integer.parseInt(LicenseDuration));
				_nl.setType(Integer.parseInt(LicenseType));
				if (LicenseDAO.updateLicense(_l, _nl)) {
					Utils.setAlert(new Alert("License updated successfully.", "success"), session);
					response.sendRedirect("list");
				} else {
					Utils.setAlert(new Alert("Cannot update license, please check fileds.", "danger"), session);
				}
			} else {
				Utils.setAlert(new Alert("Cannot update license, please check fileds.", "danger"), session);
			}
		}
		req.include(request, response);
	}
}
