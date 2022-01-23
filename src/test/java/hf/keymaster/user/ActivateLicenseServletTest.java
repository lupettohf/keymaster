package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

public class ActivateLicenseServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final ActivateLicenseServlet servlet = new ActivateLicenseServlet();

	@Test
	public void ActivateLicenseServletTest() {
		  Mockito.when(request.getPathInfo()).thenReturn("/license-key/license-key/license-key");
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		  Mockito.when(request.getParameter("ProductKey")).thenReturn("test-123-test");
		  
		  User u = new User(1, "testuser02", 
					  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
					  "testuser00@gmail.com",
					  "Nick",
					  "Name",
					   true);
		  
		  Mockito.doReturn(session).when(request).getSession();	
		  Mockito.when(session.getAttribute("user")).thenReturn(u);
		  
		  
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  assertDoesNotThrow(() -> servlet.doGet(request, response));
	}

}
