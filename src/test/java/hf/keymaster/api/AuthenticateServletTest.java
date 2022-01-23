package hf.keymaster.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.owned.OwnedLicense;
import hf.keymaster.license.owned.OwnedLicenseDAO;
import hf.keymaster.user.ActivateLicenseServlet;
import hf.keymaster.user.User;
import hf.keymaster.user.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticateServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final PrintWriter out = Mockito.mock(PrintWriter.class);
	
	private AuthenticateServelt servlet = new AuthenticateServelt();
	
	@BeforeAll
	public static void beforeAll()
	{
		Mockito.when(request.getPathInfo()).thenReturn("/T9zQmWOOb4Gw7bJC3A0lt7kw58iqsUoo\n"
				+ "/T9zQmWOOb4Gw7bJC3A0lt7kw58iqsUoo\n"
				+ "/T9zQmWOOb4Gw7bJC3A0lt7kw58iqsUoo\n");
	}
	
	@Test
	public void AuthenticateSevletTest() throws IOException
	{
		  Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		  Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		  Mockito.when(request.getParameter("hwid")).thenReturn("123-123-123");
		  Mockito.when(request.getParameter("apikey")).thenReturn("T9zQmWOOb4Gw7bJC3A0lt7kw58iqsUoo");
		  Mockito.when(response.getWriter()).thenReturn(out); 
		  
		  User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser02@gmail.com",
				  "Nick",
				  "Name",
				   false);
		  
		  Mockito.when(request.getSession()).thenReturn(session);
		  Mockito.when(request.getSession().getAttribute("user")).thenReturn(u);
		  
		  try (MockedStatic<UserDAO> mockUserDAO = Mockito.mockStatic(UserDAO.class)) {
			  	mockUserDAO.when(() -> UserDAO.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
		  }
		  try (MockedStatic<ApplicationDAO> mockApplicationDAO = Mockito.mockStatic(ApplicationDAO.class)) {
			  
			  Application a = new Application(1,1,"Test Application", "Test Desctription", "https://example.com", 0, "test-api");
			  
			  mockApplicationDAO.when(() -> ApplicationDAO.getApplication(Mockito.anyString())).thenReturn(a);
		  }
		  try (MockedStatic<OwnedLicenseDAO> mockOwnedLicenseDAO = Mockito.mockStatic(OwnedLicenseDAO.class)) {
			  
			  OwnedLicense ow = new OwnedLicense(1, 1, 1, 142342231, 1, "123-123-123");
			  List<OwnedLicense> _ow = new ArrayList<OwnedLicense>(); _ow.add(ow);
			  
			  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.getOwnedLicenses(u)).thenReturn(_ow);
			  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.isActive(ow)).thenReturn(true);
		  }
		  try (MockedStatic<LicenseDAO> mockLicenseDAO = Mockito.mockStatic(LicenseDAO.class)) {
			  Application a = new Application(1,1,"Test Application", "Test Desctription", "https://example.com", 0, "test-api");
			  License l = new License(1, 1, "Test License", "Test Description", 1, 30);
			  List<License> _l = new ArrayList<License>(); _l.add(l);
			  
			  mockLicenseDAO.when(() -> LicenseDAO.getLicenses(a)).thenReturn(_l);
		  }
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  
	}
}
