package hf.keymaster.application;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.user.User;

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
				response.sendRedirect("list");
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

		User _u = (User) session.getAttribute("user");
		Application _a = null; // Current Application
		Application _na = null; // New Application

		if (ApplicationID.isEmpty()) {
			response.sendRedirect("list");
		}
		if (!_u.isDeveloper()) {
			response.sendRedirect("/user");
		}

		try {
			_a = ApplicationDAO.getApplication(Integer.parseInt(ApplicationID));

			if (_a.getOwnerID() != _u.getID()) {
				response.sendRedirect("list");
			} else {
				session.removeAttribute("app");
				session.setAttribute("app", _a);

				if (RegenerateApikey != null) {
					ApplicationDAO.regenerateAPIKey(_a);
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
