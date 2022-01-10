package hf.keymaster.license;

import java.io.IOException;
import java.util.List;

import  jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import hf.keymaster.application.Application;
import hf.keymaster.user.User;

@WebServlet(name = "ListLicensesServlet", displayName = "ListLicensesServlet", urlPatterns = {
		"/app/manage/licenses/list" })
public class ListLicensesServlet extends HttpServlet {

	private static final long serialVersionUID = -7885180140957536213L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/listlicenses.jsp");

		User _u = (User) session.getAttribute("user");
		Application _a = (Application) session.getAttribute("app");

		if (_u == null) {
			response.sendRedirect("/login");
		}
		if (_a == null) {
			response.sendRedirect("/app/list");
		}
		;
		if (!_u.isDeveloper()) {
			response.sendRedirect("/user");
		}
		if (_a.getOwnerID() == _u.getID()) {
			List<License> _l = LicenseDAO.getLicenses(_a);
			session.removeAttribute("licenses");
			session.setAttribute("licenses", _l);
		}
		req.include(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/listlicenses.jsp");

		User _u = (User) session.getAttribute("user");
		Application _a = (Application) session.getAttribute("app");

		if (_u == null) {
			response.sendRedirect("/login");
		}
		if (_a == null) {
			response.sendRedirect("/app/list");
		}
		;
		if (!_u.isDeveloper()) {
			response.sendRedirect("/user");
		}
		if (_a.getOwnerID() == _u.getID()) {
			List<License> _l = LicenseDAO.getLicenses(_a);
			session.removeAttribute("licenses");
			session.setAttribute("licenses", _l);
		}
		req.include(request, response);
	}
	
}
