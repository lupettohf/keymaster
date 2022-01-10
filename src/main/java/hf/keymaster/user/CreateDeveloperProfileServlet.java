package hf.keymaster.user;


import java.io.IOException;

import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;
import hf.keymaster.utils.Validators;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CreateDeveloperProfileServlet", urlPatterns = { "/user/upgrade" })
public class CreateDeveloperProfileServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/upgrade.jsp");

		User _u = (User) session.getAttribute("user");

		if (_u == null) {
			response.sendRedirect("/login");
		} else {
			if(_u.isDeveloper())
			{
				response.sendRedirect("/user");
			}
			session.setAttribute("user", _u);
			
			req.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		User _u = (User) session.getAttribute("user");

		if (_u == null) {
			response.sendRedirect("/login");
		} else {
			if(_u.isDeveloper())
			{
				response.sendRedirect("/user");
			} else {
				if(UserDAO.UpgradeUser(_u))
				{
					session.removeAttribute("user");
					Utils.setAlert(new Alert("You are now a developer! Log back in.", "success"), session);
				} else {
					session.removeAttribute("user");
					Utils.setAlert(new Alert("Something went wrong while performing upgrade.", "danger"), session);
				}
			}
			response.sendRedirect("/login");			
			
		}
	}
}