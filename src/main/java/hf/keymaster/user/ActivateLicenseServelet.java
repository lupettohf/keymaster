package hf.keymaster.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.key.Key;

import hf.keymaster.license.key.KeyDAO;
import hf.keymaster.license.owned.OwnedLicenseDAO;

@WebServlet(name = "ActivateLicenseServelet", urlPatterns = { "/user/licenses/activate" })
public class ActivateLicenseServelet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/activatelicense.jsp");

		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("login");
		}
		req.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String ProductKey = request.getParameter("ProductKey");
		User user = (User) session.getAttribute("user");
		
		if (ProductKey != null && user != null) {
			Key _k = KeyDAO.getKey(ProductKey);
			
			if(_k !=null)			
			{
				if(!_k.isRedeemed())
				{
					License _lic = LicenseDAO.GetLicense(_k.getLicenseID());
					if(OwnedLicenseDAO.activateLicense(user, _lic, _k))
					{
						
					} else { /* TODO: Error in license activation */ } 
				} else { /* TODO: Key already redeemed */
			}
		} else { /* TODO: Generic error */ } 

	}
}
	

}
