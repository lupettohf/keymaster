package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

public class UserProfileServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final UserProfileServlet servlet = new UserProfileServlet();
	
	@Test
	public void UserProfileServletTest() {
		 Mockito.doReturn(session).when(request).getSession(); 
			
		 Mockito.when(request.getParameter("username")).thenReturn("pippo");
		 Mockito.when(request.getParameter("oldpassword")).thenReturn("test-123");
		 Mockito.when(request.getParameter("password")).thenReturn("test-104");
		 Mockito.when(request.getParameter("passwordrepeat")).thenReturn("test-104");
		 Mockito.when(request.getParameter("email")).thenReturn("test-123@gmail.com");
		 Mockito.when(request.getParameter("firstname")).thenReturn("Pippo");
		 Mockito.when(request.getParameter("lastname")).thenReturn("Pluto");
		
		 User u = new User(1, "pippo", 
				  "3d7e304a097d1b15771af33bc69527b0d1870ceabf29d4c0d4ca176d6a44666d",
				  "pippo@posteitaliane.it",
				  "Pippo",
				  "Pluto",
				   false);
		 
		 
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
	} 

}
