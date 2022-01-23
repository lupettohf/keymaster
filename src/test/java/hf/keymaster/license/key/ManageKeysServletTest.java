package hf.keymaster.license.key;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.mockito.Mockito;

import hf.keymaster.application.Application;
import hf.keymaster.license.License;
import hf.keymaster.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ManageKeysServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	
	private static final ManagesKeysServlet servlet = new ManagesKeysServlet();
	
	//@Test
	public void ManageKeysServletTest()
	{
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		  
		  User u = new User(1, "testuser02", 
					  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
					  "testuser00@gmail.com",
					  "Nick",
					  "Name",
					   true);
		  Application a = new Application(1, 1, "Test Application", "Test Description", "https://example.com", 0, "testkey");
		  License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		  Mockito.doReturn(session).when(request).getSession();	
		  
		  Mockito.when(request.getParameter("page")).thenReturn("1");
		  Mockito.when(request.getParameter("keys_togen")).thenReturn("1");
		  
		  Mockito.when(session.getAttribute("user")).thenReturn(u);
		  Mockito.when(session.getAttribute("app")).thenReturn(a);
		  Mockito.when(session.getAttribute("license")).thenReturn(l);
		  
		  assertDoesNotThrow(() -> servlet.doGet(request, response));
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
	}

}
