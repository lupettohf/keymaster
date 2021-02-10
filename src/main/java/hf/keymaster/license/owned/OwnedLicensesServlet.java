package hf.keymaster.license.owned;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.user.User;

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
				
				_owi.add(new OwnedLicenseInfo(
						LicenseDAO.GetLicense(ow.getID()),
						ApplicationDAO.getApplication(LicenseDAO.GetLicense(ow.getID()).getAppID()),
						ow
						));
			}
			if(_owi != null)
			{
				session.setAttribute("ownedlicenses", _owi);
			}
			
		}
		
		req.include(request, response);
	}

}
