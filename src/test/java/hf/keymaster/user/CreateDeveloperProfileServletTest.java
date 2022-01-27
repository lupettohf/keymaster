package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CreateDeveloperProfileServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final CreateDeveloperProfileServlet servlet = new CreateDeveloperProfileServlet();
	@Test
	public void CreateDeveloperProfileServletTest() {
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.doReturn(session).when(request).getSession(); 
			
		 Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		 
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   false);
		 
		 
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
	}
}
