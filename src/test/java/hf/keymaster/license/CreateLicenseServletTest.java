package hf.keymaster.license;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
