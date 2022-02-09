package hf.keymaster.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import  jakarta.servlet.ServletException;
import  jakarta.servlet.annotation.WebServlet;
import  jakarta.servlet.http.HttpServlet;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.owned.OwnedLicense;
import hf.keymaster.license.owned.OwnedLicenseDAO;
import hf.keymaster.user.UserDAO;

@WebServlet(name = "AuthenticateServelt", displayName = "AuthenticateServelt", urlPatterns = {
		"/api/v1/authenticate/*" })
public class AuthenticateServelt extends HttpServlet {
	private static final long serialVersionUID = 4730368368886662526L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String APIparam = request.getParameter("apikey");
		String path = request.getPathInfo();
		String[] paths = path.split("/");
		String ApiKey = paths[1];
		if(APIparam != null)
		{
			ApiKey = APIparam;
		}
		//http://api.piccolu.com/api/v1/authenticate/bvaowiuygfweigfkweifgawefgyiow
		response.setContentType("application/json");

		if (!(ApiKey == null || ApiKey.length() != 32)) {			
			String Username = request.getParameter("username");
			String Password = request.getParameter("password");
			String HardwareID = request.getParameter("hwid");
			PrintWriter out = response.getWriter();
			int Licenses = 0; 
			
			if ((Username != null && Password != null)) {
				int UserID = UserDAO.loginUser(Username, Password);

				if (UserID != -1) {
					Application _a = ApplicationDAO.getApplication(ApiKey);
					List<OwnedLicense> _ow = OwnedLicenseDAO.getOwnedLicenses(UserDAO.getUser(UserID));
					List<License> _lic = LicenseDAO.getLicenses(_a);
					if (_a != null && _ow != null && _lic != null) {
						for (OwnedLicense ow : _ow) {
							for (License lic : _lic) {
								if (ow.getLicenseID() == lic.getID()) {
									if (OwnedLicenseDAO.isActive(ow)) {
										if(HardwareID !=null)
										{
											if(ow.getHardwareID() == null || ow.getHardwareID().isEmpty())
											{
												OwnedLicenseDAO.setHardwareID(ow, HardwareID);
											}
										}	
										Licenses++;
										//Perché importare gson quando si possono fare questi obrobbi? 
										if(Licenses == 1 ) {out.print("[");} 
										if(Licenses > 1) {
											out.print(",");
										}
										ApiResponse apiResponse = new ApiResponse(_a.getName(), lic.getName(),
												lic.getDescription(), lic.getType(), ow.getActivationEpoch(), ow.getHardwareID());
										ObjectMapper mapper = new ObjectMapper();
										String Json = mapper.writeValueAsString(apiResponse);
										out.print(Json);
										out.print("]");
									} else {
										response.setStatus(423);
										/* License exists but expired */ }
								}
							
							}
						}
						
						out.flush();
					} else {
						response.setStatus(204);
						/* No license */ }
				} else {
					response.setStatus(401);
					/* Wrong Username or Password */ }
			} else {
				response.setStatus(503);
				/* No username or password */ }
		} else {
			response.setStatus(503);
			/* Bad Api */ }
	}
}
