package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import  jakarta.servlet.FilterChain;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

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
		  Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		  Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		  
		  User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser02@gmail.com",
				  "Nick",
				  "Name",
				   false);
		  Mockito.when(request.getSession()).thenReturn(session);
		  Mockito.when(request.getSession().getAttribute("user")).thenReturn(u);


		  Mockito.doReturn(u).when(session).getAttribute("user");
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
	  }
	  
}
