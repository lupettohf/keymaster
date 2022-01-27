package hf.keymaster.user;

import java.io.IOException;

import  jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.key.Key;

import hf.keymaster.license.key.KeyDAO;
import hf.keymaster.license.owned.OwnedLicense;
import hf.keymaster.license.owned.OwnedLicenseDAO;
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;

@WebServlet(name = "ActivateLicenseServlet", urlPatterns = { "/user/licenses/activate/*" })
public class ActivateLicenseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/activatelicense.jsp");

		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("/login");
		}
		req.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getPathInfo();
		String[] paths = path.split("/");
		String OwnedID = paths[1];
		String ProductKey = request.getParameter("ProductKey");
		User user = (User) session.getAttribute("user");

		if (ProductKey != null && user != null) {
			Key _k = KeyDAO.getKey(ProductKey);

			if (_k != null) {
				if (!_k.isRedeemed()) {
					License _lic = LicenseDAO.getLicense(_k.getLicenseID());
					if(OwnedID != null)
					{
						OwnedLicense _ow = (OwnedLicense) OwnedLicenseDAO.getOwnedLicense(Integer.parseInt(OwnedID));
						if(_ow.getUserID() == user.getID())
						{
							if(OwnedLicenseDAO.renewLicense(_ow, _k))
							{
								response.sendRedirect("/user/licenses");
								Utils.setAlert(new Alert("License renewed successfully.", "success"), session);
							}else{ 
								Utils.setAlert(new Alert("Cannot renew license.", "danger"), session);
								response.sendRedirect("/user/licenses/activate");
							}
						}else{
							Utils.setAlert(new Alert("You are not the owner of this license.", "danger"), session);
							response.sendRedirect("/user/licenses/activate");
						}
						
					}
					if (OwnedID == null && OwnedLicenseDAO.activateLicense(user, _lic, _k)) {
						response.sendRedirect("/user/licenses");
						Utils.setAlert(new Alert("License successfully activated.", "success"), session);
					} else {
						Utils.setAlert(new Alert("Cannot activate this license.", "danger"), session);
						response.sendRedirect("/user/licenses/activate");
					}
				} else { 
					Utils.setAlert(new Alert("This product code was already used.", "danger"), session);
					response.sendRedirect("/user/licenses/activate");
				}
			} else {
				Utils.setAlert(new Alert("Invalid product code.", "danger"), session);
				response.sendRedirect("/user/licenses/activate");
			}

		}
	}

}
