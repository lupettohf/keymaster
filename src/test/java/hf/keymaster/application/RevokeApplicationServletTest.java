package hf.keymaster.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.mockito.Mockito;

import hf.keymaster.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RevokeApplicationServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	
	private static final RevokeApplicationServlet servlet = new RevokeApplicationServlet();
	
	@Test
	public void RevokeApplicationServletTest()
	{
	
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(req);
		 Mockito.when(request.getParameter("manage")).thenReturn("1");
		 Mockito.when(request.getParameter("name")).thenReturn("Test Application");
		 Mockito.when(request.getParameter("userpass")).thenReturn("testuser00");
		 Mockito.when(request.getParameter("revoke")).thenReturn("1");
		
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
