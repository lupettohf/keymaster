package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class UserProfileServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final MockedStatic<UserDAO> mockUserDAO = Mockito.mockStatic(UserDAO.class);
	private static final UserProfileServlet servlet = new UserProfileServlet();
	
	@Test
	public void UserProfileServletTest() {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString());  
		 Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("oldpassword")).thenReturn("testuser00");
		 Mockito.when(request.getParameter("password")).thenReturn("testuser01");
		 Mockito.when(request.getParameter("passwordrepeat")).thenReturn("testuser01");
		 Mockito.when(request.getParameter("email")).thenReturn("testuser01@gmail.com");
		 Mockito.when(request.getParameter("firstname")).thenReturn("Nick");
		 Mockito.when(request.getParameter("lastname")).thenReturn("Name");
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   false);
		 
		 
		 mockUserDAO.when(() -> UserDAO.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
		 mockUserDAO.when(() -> UserDAO.UpdatePassword(u)).thenReturn(true);
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 mockUserDAO.close();
	} 

}
