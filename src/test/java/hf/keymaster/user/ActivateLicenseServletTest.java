package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.key.Key;
import hf.keymaster.license.key.KeyDAO;
import hf.keymaster.license.owned.OwnedLicense;
import hf.keymaster.license.owned.OwnedLicenseDAO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActivateLicenseServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final MockedStatic<KeyDAO> mockKeyDAO = Mockito.mockStatic(KeyDAO.class);
	private static final MockedStatic<LicenseDAO> mockLicenseDAO = Mockito.mockStatic(LicenseDAO.class);
	private static final MockedStatic<OwnedLicenseDAO> mockOwnedLicenseDAO = Mockito.mockStatic(OwnedLicenseDAO.class);
	private static final ActivateLicenseServlet servlet = new ActivateLicenseServlet();
	
	@Test
	public void TCGLS11_ActivateLicenseServletTest() throws IOException {
		  Mockito.when(request.getPathInfo()).thenReturn("1/1/1");
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		  Mockito.when(request.getParameter("ProductKey")).thenReturn("test-123-test");
		  
		  User u = new User(1, "testuser02", 
					  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
					  "testuser00@gmail.com",
					  "Nick",
					  "Name",
					   true);
		  Key k = new Key(1, 1, "test-123-test", true);
		  License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		  OwnedLicense ow = new OwnedLicense(1, 1, 1, 142342231, 1, "123-123-123");
		  
		  Mockito.doReturn(session).when(request).getSession();	
		  Mockito.when(session.getAttribute("user")).thenReturn(u);
		  
		  mockKeyDAO.when(() -> KeyDAO.getKey("test-123-test")).thenReturn(null); // nokey found
		  mockLicenseDAO.when(() -> LicenseDAO.getLicense(1)).thenReturn(l);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.getOwnedLicense(1)).thenReturn(ow);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.renewLicense(ow, k)).thenReturn(false);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.activateLicense(u, l, k)).thenReturn(false);
		  
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  assertDoesNotThrow(() -> servlet.doGet(request, response));
		  Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/user/licenses/activate");
		  mockKeyDAO.close();
		  mockOwnedLicenseDAO.close();
		  mockLicenseDAO.close();
	}
	
	//Key valid but redeemed
	@Test
	public void TCGLS12_ActivateLicenseServletTest() throws IOException {
		  Mockito.when(request.getPathInfo()).thenReturn("1/1/1");
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		  Mockito.when(request.getParameter("ProductKey")).thenReturn("test-123-test");
		  
		  User u = new User(1, "testuser02", 
					  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
					  "testuser00@gmail.com",
					  "Nick",
					  "Name",
					   true);
		  Key k = new Key(1, 1, "test-123-test", true);
		  License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		  OwnedLicense ow = new OwnedLicense(1, 1, 1, 142342231, 1, "123-123-123");
		  
		  Mockito.doReturn(session).when(request).getSession();	
		  Mockito.when(session.getAttribute("user")).thenReturn(u);
		  
		  mockKeyDAO.when(() -> KeyDAO.getKey("test-123-test")).thenReturn(k);
		  mockLicenseDAO.when(() -> LicenseDAO.getLicense(1)).thenReturn(l);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.getOwnedLicense(1)).thenReturn(ow);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.renewLicense(ow, k)).thenReturn(false);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.activateLicense(u, l, k)).thenReturn(false);
		  
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  assertDoesNotThrow(() -> servlet.doGet(request, response));
		  Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/user/licenses/activate");
	}
	
	//Correct key and not redeemed
	@Test
	public void TCGLS13_ActivateLicenseServletTest() throws IOException {
		  Mockito.when(request.getPathInfo()).thenReturn("1/1/1");
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		  Mockito.when(request.getParameter("ProductKey")).thenReturn("test-123-test");
		  
		  User u = new User(1, "testuser02", 
					  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
					  "testuser00@gmail.com",
					  "Nick",
					  "Name",
					   true);
		  Key k = new Key(1, 1, "test-123-test", false);
		  License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		  OwnedLicense ow = new OwnedLicense(1, 1, 1, 142342231, 1, "123-123-123");
		  
		  Mockito.doReturn(session).when(request).getSession();	
		  Mockito.when(session.getAttribute("user")).thenReturn(u);
		  
		  mockKeyDAO.when(() -> KeyDAO.getKey("test-123-test")).thenReturn(k);
		  mockLicenseDAO.when(() -> LicenseDAO.getLicense(1)).thenReturn(l);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.getOwnedLicense(1)).thenReturn(ow);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.renewLicense(ow, k)).thenReturn(true);
		  mockOwnedLicenseDAO.when(() -> OwnedLicenseDAO.activateLicense(u, l, k)).thenReturn(true);
		  
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  assertDoesNotThrow(() -> servlet.doGet(request, response));
		  Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/user/licenses");
		 
	}
	
	@AfterAll
	public static void closeMocks()
	{
		  mockKeyDAO.close();
		  mockOwnedLicenseDAO.close();
		  mockLicenseDAO.close();
	}

}
