package hf.keymaster.license;

import  jakarta.servlet.http.HttpServletRequest;
import  jakarta.servlet.http.HttpServletResponse;
import  jakarta.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import hf.keymaster.user.RegisterServlet;

public class CreateLicenseServletTest {

	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RegisterServlet servlet = new RegisterServlet();
	
	@Test
	public void CreateLicenseServletTest() {
		// TODO Auto-generated constructor stub
	}

}
