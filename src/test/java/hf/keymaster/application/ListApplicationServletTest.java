package hf.keymaster.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import hf.keymaster.user.User;
import hf.keymaster.user.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListApplicationServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final ListApplicationServlet servlet = new ListApplicationServlet();
	private static final MockedStatic<ApplicationDAO> mockApplicationDAO = Mockito.mockStatic(ApplicationDAO.class);
	
	@Test
	public void ListApplicationServletTest()
	{
		Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString());   
		User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   false);
		Application a = new Application(1,1,"Test Application", "Test Desctription", "https://example.com", 0, "test-api"); 
		List<Application> _a = new ArrayList<Application>(); _a.add(a);
		mockApplicationDAO.when(() -> ApplicationDAO.getApplications(u)).thenReturn(_a);
		 
		Mockito.doReturn(u).when(session).getAttribute("user");
		assertDoesNotThrow(() -> servlet.doGet(request, response));
		mockApplicationDAO.close();
	}
}
