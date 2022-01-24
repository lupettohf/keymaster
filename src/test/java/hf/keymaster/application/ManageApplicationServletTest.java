package hf.keymaster.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.key.Key;
import hf.keymaster.license.owned.OwnedLicense;
import hf.keymaster.license.owned.OwnedLicenseDAO;
import hf.keymaster.user.User;
import hf.keymaster.user.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ManageApplicationServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final ManageApplicationServlet servlet = new ManageApplicationServlet();
	private static final MockedStatic<ApplicationDAO> mockApplicationDAO = Mockito.mockStatic(ApplicationDAO.class);
	private static final MockedStatic<LicenseDAO> mockLicenseDAO = Mockito.mockStatic(LicenseDAO.class);
	private static final MockedStatic<UserDAO> mockUserDAO = Mockito.mockStatic(UserDAO.class);
	private static final MockedStatic<OwnedLicenseDAO> mockOwnedLicenseDAO = Mockito.mockStatic(OwnedLicenseDAO.class);
	
	@Test
	public void ManageApplicationServletTest()
	{
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString());  
		 //Dettagli Applicativo
		 Mockito.when(request.getParameter("name")).thenReturn("Test Application");
		 Mockito.when(request.getParameter("description")).thenReturn("Test Application Description");
		 Mockito.when(request.getParameter("website")).thenReturn("https://example.com/product");
		 Mockito.when(request.getParameter("version")).thenReturn("1");
		 Mockito.when(request.getParameter("manage")).thenReturn("1");
		 //Regenerate API
		 Mockito.when(request.getParameter("regenerate_api")).thenReturn("true");
		 //Add user to license
		 Mockito.when(request.getParameter("add_user")).thenReturn("true");
		 Mockito.when(request.getParameter("license")).thenReturn("1");
		 Mockito.when(request.getParameter("tgtuser")).thenReturn("testuser02");
		 //Submit
		 Mockito.when(request.getParameter("update_details")).thenReturn("1");
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);	 
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 Application a = new Application(1,1,"Test Application", "Test Desctription", "https://example.com", 0, "test-api");
			  
		 mockApplicationDAO.when(() -> ApplicationDAO.getApplication(1)).thenReturn(a);
		 mockApplicationDAO.when(() -> ApplicationDAO.regenerateAPIKey(a)).thenReturn("testapi-new");
		 mockUserDAO.when(() -> UserDAO.getUserByName("testuser00")).thenReturn(u);
		
		 License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		 List<License> _l = new ArrayList<License>(); _l.add(l);
		 
		 Key k = new Key(-1, 1, "manual_upgrade", true);
		 mockLicenseDAO.when(() -> LicenseDAO.getLicenses(a)).thenReturn(_l);
		 mockLicenseDAO.when(() -> LicenseDAO.getLicense(1)).thenReturn(l);
		 mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.activateLicense(u, l, k)).thenReturn(true);
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
			
		 mockLicenseDAO.close();
		 mockOwnedLicenseDAO.close();
		 mockApplicationDAO.close();
		 mockUserDAO.close();
	}
	
}
