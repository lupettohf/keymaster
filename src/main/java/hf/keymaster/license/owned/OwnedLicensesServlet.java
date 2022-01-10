package hf.keymaster.license.owned;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.user.User;
import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Utils;

@WebServlet(name = "OwnedLicensesServlet", displayName = "OwnedLicensesServlet", urlPatterns = {
"/user/licenses" })
public class OwnedLicensesServlet extends HttpServlet{

	private static final long serialVersionUID = -6702372371122428752L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/ownedlicenses.jsp");

		User _u = (User) session.getAttribute("user");

		if (_u == null) {
			response.sendRedirect("/login");
		}
		
		List<OwnedLicense> _ow = OwnedLicenseDAO.getOwnedLicenses(_u);
		List<OwnedLicenseInfo> _owi = new ArrayList<OwnedLicenseInfo>();
		
		if(_ow != null) { 
			for(OwnedLicense ow : _ow)
			{
				System.out.println("ownedlicese");
				License license = LicenseDAO.GetLicense(ow.getLicenseID());
				Application application = ApplicationDAO.getApplication(license.getAppID());
				
				if(license !=null && application != null) {
					_owi.add(new OwnedLicenseInfo(license, application, ow));
				}				
			}
			if(_owi != null)
			{
				session.removeAttribute("ownedlicenses");
				session.setAttribute("ownedlicenses", _owi);
			}
		}
		req.include(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
			String License_Remove = request.getParameter("remove");
			String License_Renew = request.getParameter("renew");
			HttpSession session = request.getSession();
			RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/ownedlicenses.jsp");

			User _u = (User) session.getAttribute("user");

			if (_u == null) {
				response.sendRedirect("/login");
			}
			if(License_Remove !=null)
			{
				if(OwnedLicenseDAO.deleteLicense(_u, LicenseDAO.GetLicense(Integer.parseInt(License_Remove))))
				{
					Utils.setAlert(new Alert("License removed successfully.", "success"), session);
				} else {
					Utils.setAlert(new Alert("Cannot delete license, please contact an administrator", "danger"), session);
				}
				
			}
			if(License_Renew !=null)
			{
				response.sendRedirect("activate/" + License_Renew);
			}
			req.include(request, response);
	}
	
	
}
