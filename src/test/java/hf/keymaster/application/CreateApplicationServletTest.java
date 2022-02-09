package hf.keymaster.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CreateApplicationServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final MockedStatic<ApplicationDAO> mockApplicationDAO = Mockito.mockStatic(ApplicationDAO.class);
	private static final CreateApplicationServlet servlet = new CreateApplicationServlet();
	
	//Correct
	@Test
	public void TCGLS34_CreateApplicationServletTest() throws IOException
	{
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("name")).thenReturn("Test Application");
		 Mockito.when(request.getParameter("description")).thenReturn("Test Application Description");
		 Mockito.when(request.getParameter("website")).thenReturn("https://example.com/product");
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 
		 mockApplicationDAO.when(() -> ApplicationDAO.createApplication(u, "Test Application", "Test Description", "https://example.com/product")).thenReturn(true);

		 Mockito.doReturn(u).when(session).getAttribute("user");
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/app/new");
		 
		 mockApplicationDAO.close();
	}
}
