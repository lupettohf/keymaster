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
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;

@WebServlet(name = "CreateApplicationServlet", displayName = "CreateApplicationServlet", urlPatterns = { "/app/new" })
public class CreateApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = -473138506911894741L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/createapp.jsp");

		User _u = (User) session.getAttribute("user");

		if (_u == null) {
			response.sendRedirect("/login");
		}
		if (!_u.isDeveloper()) {
			response.sendRedirect("/user");
		}

		req.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String App_Name = request.getParameter("name");
		String App_Description = request.getParameter("description");
		String App_Website = request.getParameter("website");
		String App_Version = request.getParameter("version");
		HttpSession session = request.getSession();

		User _u = (User) session.getAttribute("user");

		if (_u != null) {
			if (_u.isDeveloper()) {
				if (ApplicationDAO.createApplication(_u, App_Name, App_Description, App_Website)) {
					Utils.setAlert(new Alert("Application created successfully.", "success"), session);
					response.sendRedirect("/app/list");
				} else {
					Utils.setAlert(new Alert("Cannot create application, please check the fileds.", "danger"), session);
				}
			}
		}
	}
}
