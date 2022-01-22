package hf.keymaster.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.owned.OwnedLicense;
import hf.keymaster.license.owned.OwnedLicenseDAO;
import hf.keymaster.user.ActivateLicenseServlet;
import hf.keymaster.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticateServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private ApplicationDAO ApplicationDAO = Mockito.mock(ApplicationDAO.class);
	private LicenseDAO LicenseDAO = Mockito.mock(LicenseDAO.class);
	private OwnedLicenseDAO OwnedLicenseDAO = Mockito.mock(OwnedLicenseDAO.class);
	private AuthenticateServelt servlet = new AuthenticateServelt();
	
	@BeforeAll
	public static void beforeAll()
	{
		Mockito.when(request.getPathInfo()).thenReturn("/test-api/test-api/test-api");
	}
	
	@Test
	public void AuthenticateSevletTest()
	{
		  Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		  Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		  Mockito.when(request.getParameter("hwid")).thenReturn("123-123-123");
		  
		  User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser02@gmail.com",
				  "Nick",
				  "Name",
				   false);
		  
		  Mockito.when(request.getSession()).thenReturn(session);
		  Mockito.when(request.getSession().getAttribute("user")).thenReturn(u);
		
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  
	}
}
