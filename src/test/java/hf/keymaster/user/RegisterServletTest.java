package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

public class RegisterServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RegisterServlet servlet = new RegisterServlet();
	
	@Test
	public void RegisterServletTest() {
		 Mockito.doReturn(session).when(request).getSession(); 
		
		 Mockito.when(request.getParameter("username")).thenReturn("pippo");
		 Mockito.when(request.getParameter("password")).thenReturn("test-123");
		 Mockito.when(request.getParameter("password_confirm")).thenReturn("test-123");
		 Mockito.when(request.getParameter("email")).thenReturn("test-123@gmail.com");
		
		 
		 
		 //assertDoesNotThrow(() -> servlet.doPost(request, response));
	}

}
