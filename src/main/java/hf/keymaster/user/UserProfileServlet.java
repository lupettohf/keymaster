package hf.keymaster.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.utils.Validators;

@WebServlet(name = "UserProfileServlet", urlPatterns = { "/user" })
public class UserProfileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/user.jsp");

		User _u = (User) session.getAttribute("user");

		if (_u == null) {
			response.sendRedirect("login");
		} else {
			session.setAttribute("user", _u);
			req.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String FirstName = request.getParameter("firstname");
		String LastName = request.getParameter("lastname");
		String OldPassword = request.getParameter("oldpassword");
		String NewPassword = request.getParameter("password");
		String NewPasswordRepeat = request.getParameter("passwordrepeat");

		User _u = (User) session.getAttribute("user");
		User _nu = _u;
		if (_u == null) {
			response.sendRedirect("login");
		} else {
			if (!OldPassword.isEmpty() && Validators.ValidatePassword(NewPasswordRepeat, NewPassword)) {
				if (UserDAO.loginUser(_u.getUsername(), OldPassword) != -1) {
					_nu.setPassword(NewPassword);
					UserDAO.UpdatePassword(_nu);
				}

			}
			if (!FirstName.isEmpty() && !LastName.isEmpty()) {
				_nu.setFirstName(FirstName);
				_nu.setLastName(LastName);
				UserDAO.SetFirstLastName(_nu);				
			}
			response.sendRedirect("/user");
		}
	}
}
