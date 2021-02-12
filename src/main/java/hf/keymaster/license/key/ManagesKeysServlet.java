package hf.keymaster.license.key;

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
import hf.keymaster.license.License;
import hf.keymaster.user.User;
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;

@WebServlet(name = "ManagesKeysServlet", displayName = "ManagesKeysServlet", urlPatterns = {
		"/app/manage/licenses/keys/manage" })
public class ManagesKeysServlet extends HttpServlet {

	private static final long serialVersionUID = 4878124569715692361L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/managekeys.jsp");

		String Page = request.getParameter("page");

		User _u = (User) session.getAttribute("user");
		Application _a = (Application) session.getAttribute("app");
		License _l = (License) session.getAttribute("license");
		int TotalPages = 1;
		int CurrentPage = 0;

		if (!(_u == null || _l == null) && (_u.getID() == _a.getOwnerID() && _l.getAppID() == _a.getID())) {
			int TotalKeys = KeyDAO.countKeys(_l);
			int TotalReedemedKeys = KeyDAO.countRedemedKeys(_l);

			session.setAttribute("totalkeys", TotalKeys);
			session.setAttribute("totalredeemed", TotalReedemedKeys);
			if (TotalKeys > 0) {
				if (Page != null) {
					CurrentPage = Integer.parseInt(Page);
				}
				TotalPages = TotalKeys / 10;
				if (CurrentPage > TotalPages) {
					CurrentPage = TotalPages;
				}
				List<Key> _k = KeyDAO.getKeys(_l, false, CurrentPage * 10);
				session.setAttribute("curpage", CurrentPage);
				session.setAttribute("pages", TotalPages);
				session.setAttribute("keys", _k);
			}
		} else {
			response.sendRedirect("/user");
		}
		req.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String KeysToGenerate = request.getParameter("keys_togen");
		String Action = request.getParameter("action");

		User _u = (User) session.getAttribute("user");
		Application _a = (Application) session.getAttribute("app");
		License _l = (License) session.getAttribute("license");

		if (!(_u == null || _l == null) && (_u.getID() == _a.getOwnerID() && _l.getAppID() == _a.getID())) {
			int TotalKeys = KeyDAO.countKeys(_l);
			int TotalReedemedKeys = KeyDAO.countRedemedKeys(_l);

			session.setAttribute("totalkeys", TotalKeys);
			session.setAttribute("totalredeemed", TotalReedemedKeys);

			if (KeysToGenerate != null && Action != null) {
				int ToGen = Integer.parseInt(KeysToGenerate);
				if ((ToGen > 1 && ToGen <= 500) && Action.equals("generate")) {
					for (int i = 0; i < ToGen; i++) {
						KeyDAO.createKey(_l);
					}
					response.sendRedirect("/app/manage/licenses/keys/manage");
				} else {
					Utils.setAlert(new Alert("You can only generate 500 product keys per request.", "danger"), session);
				}
			}

		} else {
			response.sendRedirect("/user");
		}
	}
}
