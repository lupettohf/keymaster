package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

public class LoginServleTest {

	  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	  private static final HttpSession session = Mockito.mock(HttpSession.class);
	  private static final LoginServlet servlet = new LoginServlet();

	  @BeforeAll
	  public static void beforeAll() {
	    Mockito.doReturn(session).when(request).getSession();
	  }
	  
	  @Test
	  public void testLogin()
	  {
		  Mockito.when(request.getParameter("username")).thenReturn("pippo");
		  Mockito.when(request.getParameter("password")).thenReturn("qualunquecosa");
		  
		  User u = new User(1, "pippo", 
				  "3d7e304a097d1b15771af33bc69527b0d1870ceabf29d4c0d4ca176d6a44666d",
				  "pippo@posteitaliane.it",
				  "Pippo",
				  "Pluto",
				   false);
		  
		  Mockito.doReturn(u).when(session).getAttribute("user");
		 // assertDoesNotThrow(() -> servlet.doPost(request, response));
	  }
	  
}
