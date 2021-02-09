package hf.keymaster.license;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
}
