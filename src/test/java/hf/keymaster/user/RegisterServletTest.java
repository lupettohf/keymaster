package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import jakarta.servlet.RequestDispatcher;
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
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final RegisterServlet servlet = new RegisterServlet();
	
	@Test
	public void RegisterServletTest() {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		 Mockito.when(request.getParameter("password_confirm")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("email")).thenReturn("testuser02@gmail.com");
		
		 
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
	}

}
