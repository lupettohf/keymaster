package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

import org.junit.After;

import  jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class LoginServleTest {

	  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	  private static final HttpSession session = Mockito.mock(HttpSession.class);
	  private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	  private static final LoginServlet servlet = new LoginServlet();
	  private static final MockedStatic<UserDAO> mockUserDAO = Mockito.mockStatic(UserDAO.class);
	  
	  @Test
	  public void TGCU21_LoginServleTest() throws IOException
	  {
		  Mockito.doReturn(session).when(request).getSession();
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString());  
		  Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		  Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		  
		  User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser02@gmail.com",
				  "Nick",
				  "Name",
				   false);
		  Mockito.when(request.getSession()).thenReturn(session);
		 
		  
		  mockUserDAO.when(() -> UserDAO.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
	      mockUserDAO.when(() -> UserDAO.getUser(1)).thenReturn(u);
		  
		  
		  assertDoesNotThrow(() -> servlet.doGet(request, response));
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("user");
	  }
	  
	  //Login failure
	  @Test
	  public void TGCU22_LoginServleTest() throws IOException
	  {
		  Mockito.doReturn(session).when(request).getSession();
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString());  
		  Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		  Mockito.when(request.getParameter("password")).thenReturn("pass");
		  
		  User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser02@gmail.com",
				  "Nick",
				  "Name",
				   false);
		  Mockito.when(request.getSession()).thenReturn(session);
		 
		  
		  mockUserDAO.when(() -> UserDAO.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(-1);
	      mockUserDAO.when(() -> UserDAO.getUser(1)).thenReturn(null);
		  
		  
		  assertDoesNotThrow(() -> servlet.doPost(request, response));
		  Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("login");
	  }
	  
	  @AfterAll
	  public static void closeMocks()
	  {
		  mockUserDAO.close();
	  }
	  

}
