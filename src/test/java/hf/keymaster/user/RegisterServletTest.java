package hf.keymaster.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

import org.junit.After;

import jakarta.servlet.RequestDispatcher;
import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.utils.Alert;
import hf.keymaster.utils.Validators;

public class RegisterServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final RegisterServlet servlet = new RegisterServlet();
	private static final MockedStatic<UserDAO> mockUserDAO = Mockito.mockStatic(UserDAO.class);
	private static final MockedStatic<Validators> mockValidators = Mockito.mockStatic(Validators.class);
	
	@Test
	public void TCGU11_RegisterServletTest() throws IOException {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		 Mockito.when(request.getParameter("password_confirm")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("email")).thenReturn("test");
		 
		 mockUserDAO.when(() -> UserDAO.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		 
		 
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/register");
	}
	
	@Test
	public void TCGU12_RegisterServletTest() throws IOException {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("username")).thenReturn("a");
		 Mockito.when(request.getParameter("password")).thenReturn("");
		 Mockito.when(request.getParameter("password_confirm")).thenReturn("");
		 Mockito.when(request.getParameter("email")).thenReturn("testuser02gmail.com");
		 
		 mockUserDAO.when(() -> UserDAO.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		 
		 
		 
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/register");

	} 
	
	@Test
	public void TCGU13_RegisterServletTest() throws IOException {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("password")).thenReturn("pass");
		 Mockito.when(request.getParameter("password_confirm")).thenReturn("pass");
		 Mockito.when(request.getParameter("email")).thenReturn("testuser02gmail.com");
		 
		 mockUserDAO.when(() -> UserDAO.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		 
		 
	
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/register");

	} 
	
	@Test
	public void TCGU14_RegisterServletTest() throws IOException {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		 Mockito.when(request.getParameter("password_confirm")).thenReturn("pass");
		 Mockito.when(request.getParameter("email")).thenReturn("testuser02gmail.com");
		 
		 mockUserDAO.when(() -> UserDAO.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		 
		 
		
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/register");

	} 
	
	@Test
	public void TCGU15_RegisterServletTest() throws IOException {
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 Mockito.when(request.getParameter("username")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("password")).thenReturn("testuser00");
		 Mockito.when(request.getParameter("password_confirm")).thenReturn("testuser02");
		 Mockito.when(request.getParameter("email")).thenReturn("testuser02@gmail.com");
		 
		 mockUserDAO.when(() -> UserDAO.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		 mockValidators.when(() -> Validators.ValidateUsername(Mockito.anyString())).thenReturn(true);
		 mockValidators.when(() -> Validators.ValidatePassword(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
		 mockValidators.when(() -> Validators.ValidateEmail(Mockito.anyString())).thenReturn(true);
		 
		 assertDoesNotThrow(() -> servlet.doPost(request, response));
		 Mockito.verify(response, Mockito.atLeastOnce()).sendRedirect("/login");
	}
	
	@AfterAll
	public static void closeMocks()
	{
		 mockUserDAO.close();
		 mockValidators.close();
	}
	
}
