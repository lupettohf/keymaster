package hf.keymaster.license;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import hf.keymaster.application.Application;
import hf.keymaster.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListLicenseServletTest {
	private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
	private static final HttpSession session = Mockito.mock(HttpSession.class);
	private static final RequestDispatcher req = Mockito.mock(RequestDispatcher.class);
	private static final ListLicensesServlet servlet = new ListLicensesServlet();
	private static final MockedStatic<LicenseDAO> mockLicenseDAO = Mockito.mockStatic(LicenseDAO.class);
	@Test
	public void ListLicenseServletTest()
	{
		 Mockito.doReturn(session).when(request).getSession(); 
		 Mockito.doReturn(req).when(request).getRequestDispatcher(Mockito.anyString()); 
		 
		 User u = new User(1, "testuser02", 
				  "b674f5285a0587792b1f887e727a29b1808ef510070a37408b3c88e1be4ca71e",
				  "testuser00@gmail.com",
				  "Nick",
				  "Name",
				   true);
		 Application a = new Application(1, 1, "Test Application", "Test Description", "https://example.com", 0, "test-api");
		 License l = new License(1, 1, "Test License", "Test Description", 1, 30);
		 License _l = new License(1, 1, "Test License", "Test Description Updated", 2, 90);
		 List<License> licenses = new ArrayList<License>(); licenses.add(l);
		 
		 mockLicenseDAO.when(() -> LicenseDAO.getLicenses(a)).thenReturn(licenses);
		 mockLicenseDAO.when(() -> LicenseDAO.updateLicense(l, _l)).thenReturn(true);
		 Mockito.doReturn(u).when(session).getAttribute("user");
		 Mockito.doReturn(a).when(session).getAttribute("app");
		 assertDoesNotThrow(() -> servlet.doGet(request, response));
		 assertDoesNotThrow(() -> servlet.doPost(request, response));	 
		 mockLicenseDAO.close();
	}
}
