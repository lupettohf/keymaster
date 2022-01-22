package hf.keymaster.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.mockito.Mockito;

import hf.keymaster.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CreateApplicationServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final CreateApplicationServlet servlet = new CreateApplicationServlet();
	
	@Test
	public void CreateApplicationServletTest()
	{
		Mockito.doReturn(session).when(request).getSession(); 
		
		 Mockito.when(request.getParameter("name")).thenReturn("Test Application");
		 Mockito.when(request.getParameter("description")).thenReturn("Test Application Description");
		 Mockito.when(request.getParameter("website")).thenReturn("https://example.com/product");
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 
		 
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
	}
}
