package hf.keymaster.application;

import java.io.IOException;

import  jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import hf.keymaster.user.User;
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;
import hf.keymaster.utils.Validators;

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
		HttpSession session = request.getSession();

		User _u = (User) session.getAttribute("user");

		if (_u != null) {
			if (_u.isDeveloper()) {
				System.out.print("porcodio");
				if(Validators.ValidateURL(App_Website)) {				
					if (ApplicationDAO.createApplication(_u, App_Name, App_Description, App_Website)) {						
						Utils.setAlert(new Alert("Application created successfully.", "success"), session);
						response.sendRedirect("/app/list");
					} else {
						Utils.setAlert(new Alert("Cannot create application, please check the fileds.", "danger"), session);
					}
				}else {
					Utils.setAlert(new Alert("Invalid URL scheme.", "danger"), session);
				}
				
			}
		}
	}
}
