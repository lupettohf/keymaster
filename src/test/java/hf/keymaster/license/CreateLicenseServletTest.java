package hf.keymaster.license;

import jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.mockito.Mockito;

import hf.keymaster.application.Application;
import hf.keymaster.application.CreateApplicationServlet;
import hf.keymaster.user.RegisterServlet;
import hf.keymaster.user.User;

public class CreateLicenseServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final CreateLicenseServlet servlet = new CreateLicenseServlet();
	
	@Test
	public void CreateLicenseServletTest() {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher("/skeletons/pages/newlicense.jsp"); 
		 Mockito.when(request.getParameter("name")).thenReturn("Test License");
		 Mockito.when(request.getParameter("description")).thenReturn("Test License Description");
		 Mockito.when(request.getParameter("type")).thenReturn("1");
		 Mockito.when(request.getParameter("duration")).thenReturn("30");
		 
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 Application a = new Application(1, 1, "Test Application", "Test Description", "https://example.com", 0, "test-api");
		 
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 Mockito.doReturn(a).when(session).getAttribute("app");
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
	}

}
