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

@WebServlet(name = "RegisterServlet", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -8078715490524280378L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/register.jsp");
		req.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		String Password_confirm = request.getParameter("password_confirm");
		String EMail = request.getParameter("email");
		HttpSession session = request.getSession();

		if (session.getAttribute("User") != null) {
			response.sendRedirect("/user");
		}

		if (Validators.ValidateUsername(Username) && Validators.ValidatePassword(Password, Password_confirm)
				&& Validators.ValidateEmail(EMail)) {
			if (UserDAO.registerUser(Username, Password, EMail)) {
				Utils.setAlert(new Alert("Registred successfully, please login.", "success"), session);
				response.sendRedirect("/login");				
				
			} else {
				Utils.setAlert(new Alert("Username or email already registred.", "danger"), session);
				response.sendRedirect("/register");
		
			}
		}
	}

}
