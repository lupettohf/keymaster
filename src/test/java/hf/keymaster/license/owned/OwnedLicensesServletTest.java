package hf.keymaster.license.owned;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.owned.OwnedLicensesServlet;
import hf.keymaster.user.User;
import hf.keymaster.user.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OwnedLicensesServletTest {
	
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private OwnedLicensesServlet servlet = new OwnedLicensesServlet();
	private static final MockedStatic<OwnedLicenseDAO> mockOwnedLicenseDAO = Mockito.mockStatic(OwnedLicenseDAO.class);
	private static final MockedStatic<LicenseDAO> mockLicenseDAO = Mockito.mockStatic(LicenseDAO.class);
	private static final MockedStatic<ApplicationDAO> mockApplicationDAO = Mockito.mockStatic(ApplicationDAO.class);
	@Test
	public void OwnedLicensesServletTest()
	{
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("remove")).thenReturn("1");
		 Mockito.when(request.getParameter("renew")).thenReturn("1");
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 License l = new License(1, 1, "Test License", "Test Description", 1, 30);
	     List<License> _l = new ArrayList<License>(); _l.add(l);
		 Application a = new Application(1, 1, "Test Application", "Test Description", "https://example.com", 0, "test-api");
		 OwnedLicense ow = new OwnedLicense(1, 1, 1, 142342231, 1, "123-123-123");
		 List<OwnedLicense> _ow = new ArrayList<OwnedLicense>(); _ow.add(ow);
		 mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.getOwnedLicenses(u)).thenReturn(_ow);
		 mockLicenseDAO.when(() -> LicenseDAO.getLicense(1)).thenReturn(l);
		 mockApplicationDAO.when(() -> ApplicationDAO.getApplication(1)).thenReturn(a);
		 mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.deleteLicense(u, l)).thenReturn(true);
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 mockOwnedLicenseDAO.close();
		 mockLicenseDAO.close();
		 mockApplicationDAO.close();
	}
}
