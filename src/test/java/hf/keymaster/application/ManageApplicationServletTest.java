package hf.keymaster.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.mockito.Mockito;

import hf.keymaster.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ManageApplicationServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final CreateApplicationServlet servlet = new CreateApplicationServlet();
	
	@Test
	public void ManageApplicationServletTest()
	{
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher("/skeletons/pages/manageapp.jsp"); 
		 //Dettagli Applicativo
		 Mockito.when(request.getParameter("manage")).thenReturn("1");
		 Mockito.when(request.getParameter("name")).thenReturn("Test Application");
		 Mockito.when(request.getParameter("description")).thenReturn("Test Application Description");
		 Mockito.when(request.getParameter("website")).thenReturn("https://example.com/product");
		 Mockito.when(request.getParameter("version")).thenReturn("1");
		 //Regenerate API
		 Mockito.when(request.getParameter("regenerate_api")).thenReturn("1");
		 //Add user to license
		 Mockito.when(request.getParameter("add_user")).thenReturn("1");
		 Mockito.when(request.getParameter("license")).thenReturn("1");
		 Mockito.when(request.getParameter("tgtuser")).thenReturn("testuser02");
		 //Submit
		 Mockito.when(request.getParameter("update_details")).thenReturn("1");
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 
		 
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 
	}
}
