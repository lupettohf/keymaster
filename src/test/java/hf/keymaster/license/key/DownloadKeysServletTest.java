package hf.keymaster.license.key;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.application.Application;
import hf.keymaster.license.License;
import hf.keymaster.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DownloadKeysServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final ServletOutputStream outputStream = Mockito.mock(ServletOutputStream.class);
	private static final MockedStatic<KeyDAO> mockKeyDAO = Mockito.mockStatic(KeyDAO.class);
	private static final DownloadKeysServelt servlet = new DownloadKeysServelt();
	
	@Test
	public void DownloadKeysServletTest() throws IOException
	{
		  Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		  Mockito.doReturn(outputStream).when(response).getOutputStream();
		  Mockito.when(response.getOutputStream()).thenReturn(outputStream); 
		  User u = new User(1, "testuser02", 
					  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
					  "testuser00@gmail.com",
					  "Nick",
					  "Name",
					   true);
		  Application a = new Application(1, 1, "Test Application", "Test Description", "https://example.com", 0, "testkey");
		  License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		  Mockito.doReturn(session).when(request).getSession();	
		  Mockito.when(session.getAttribute("user")).thenReturn(u);
		  Mockito.when(session.getAttribute("app")).thenReturn(a);
		  Mockito.when(session.getAttribute("license")).thenReturn(l);
		  List<Key> keys = new ArrayList<Key>();
		  
		  for(int i = 1; i <= 10; i++)
		  {
			  Key _k;
			  if(i % 2 == 0)
			  {
				   _k = new Key(1, i, "123-123-123", false);
			  } else {
				   _k = new Key(1, i, "123-123-123", true);
			  }
			  
			  keys.add(_k);
		  }
		  mockKeyDAO.when(() -> KeyDAO.getKeys(l, false)).thenReturn(keys);
		  assertDoesNotThrow(() -> servlet.doGet(request, response));
		  mockKeyDAO.close();
	}
}
