package hf.keymaster.user;

import java.io.IOException;

import  jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;
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
			response.sendRedirect("/login");
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
					if(UserDAO.UpdatePassword(_nu))
					{
						Utils.setAlert(new Alert("Password updated successfully", "success"), session);
					} else {
						Utils.setAlert(new Alert("Cannot update password", "danger"), session);
					}
				} else {
					Utils.setAlert(new Alert("Passwords don't match or you old password is wrong.", "danger"), session);
				}

			}
			if (!FirstName.isEmpty() && !LastName.isEmpty()) {
				_nu.setFirstName(FirstName);
				_nu.setLastName(LastName);
				if(UserDAO.SetFirstLastName(_nu))
				{
					Utils.setAlert(new Alert("Personal details updated.", "success"), session);
				} else {
					Utils.setAlert(new Alert("Cannot update your personal details", "danger"), session);
				}
				
			}
			response.sendRedirect("user");
		}
	}
}
