package hf.keymaster.license;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.application.Application;
import hf.keymaster.application.ApplicationDAO;
import hf.keymaster.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ManageLicenseServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final ManageLicenseServlet servlet = new ManageLicenseServlet();
	private static final MockedStatic<LicenseDAO> mockLicenseDAO = Mockito.mockStatic(LicenseDAO.class);
	
	@Test
	public void TCGL11_ManageLicenseServletTest()
	{
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("name")).thenReturn("Test License");
		 Mockito.when(request.getParameter("description")).thenReturn("Test License Description");
		 Mockito.when(request.getParameter("type")).thenReturn("1");
		 Mockito.when(request.getParameter("duration")).thenReturn(""); //Miss duration
		 Mockito.when(request.getParameter("manage")).thenReturn("1");
		 
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 Application a = new Application(1, 1, "Test Application", "Test Description", "https://example.com", 0, "test-api");
		 License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		 License _l = new License(1, 1, "Test License", "Test Description Updated", 2, 0);
		 List<License> licenses = new ArrayList<License>(); licenses.add(l);
		 mockLicenseDAO.when(() -> LicenseDAO.getLicense(1)).thenReturn(l);
		 mockLicenseDAO.when(() -> LicenseDAO.updateLicense(l, _l)).thenReturn(false);
		 
		 Mockito.when(session.getAttribute("user")).thenReturn(u);
		 Mockito.when(session.getAttribute("app")).thenReturn(a);
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 Mockito.doReturn(a).when(session).getAttribute("app");
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 assertDoesNotThrow(() -> servlet.doPost(request, response));	 
		
	}
	
	//Correct
	@Test
	public void TCGL12_ManageLicenseServletTest()
	{
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("name")).thenReturn("Test License");
		 Mockito.when(request.getParameter("description")).thenReturn("Test License Description");
		 Mockito.when(request.getParameter("type")).thenReturn("1");
		 Mockito.when(request.getParameter("duration")).thenReturn("90");
		 Mockito.when(request.getParameter("manage")).thenReturn("1");
		 
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 Application a = new Application(1, 1, "Test Application", "Test Description", "https://example.com", 0, "test-api");
		 License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		 License _l = new License(1, 1, "Test License", "Test Description Updated", 2, 90);
		 List<License> licenses = new ArrayList<License>(); licenses.add(l);
		 mockLicenseDAO.when(() -> LicenseDAO.getLicense(1)).thenReturn(l);
		 mockLicenseDAO.when(() -> LicenseDAO.updateLicense(l, _l)).thenReturn(true);
		 
		 Mockito.when(session.getAttribute("user")).thenReturn(u);
		 Mockito.when(session.getAttribute("app")).thenReturn(a);
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 Mockito.doReturn(a).when(session).getAttribute("app");
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 assertDoesNotThrow(() -> servlet.doPost(request, response));	 
		 mockLicenseDAO.close();
	}
}
