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

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/login.jsp");

		User user = (User) session.getAttribute("user");

		if (user != null) {
			response.sendRedirect("user");
		}
		req.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		
		if (!(Username.isEmpty() || Password.isBlank())) {
			int ID = UserDAO.loginUser(Username, Password);

			if (ID != -1) {
				session.setAttribute("user", UserDAO.getUser(ID));
				response.sendRedirect("user");
			} else {
				response.sendRedirect("login");
				Utils.setAlert(new Alert("Wrong username or password.", "danger"), session);
			}
		}

	}

}
