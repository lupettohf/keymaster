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

import hf.keymaster.user.User;

@WebServlet(name = "ListApplicationServlet", displayName = "ListApplicationServlet", urlPatterns = { "/app/list" })
public class ListApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = -6146271747637218235L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/listapp.jsp");

		User _u = (User) session.getAttribute("user");

		if (_u != null) {
			if (!_u.isDeveloper()) {
				response.sendRedirect("/user");
			}
			List<Application> AppList = ApplicationDAO.getApplications(_u);

			session.setAttribute("applist", AppList);
		} else {
			response.sendRedirect("/login");
		}

		req.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
