package hf.keymaster.license.key;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hf.keymaster.application.Application;
import hf.keymaster.license.License;
import hf.keymaster.user.User;


@WebServlet(name = "DownloadKeysServelt", displayName="DownloadKeysServelt", urlPatterns = {"/app/manage/licenses/keys/manage/download"})
public class DownloadKeysServelt extends HttpServlet{
	private static final long serialVersionUID = 2194026705383129556L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();	
				
		User _u = (User) session.getAttribute("user");
		Application _a = (Application) session.getAttribute("app");
		License _l = (License) session.getAttribute("license");
		
		if(!(_u == null || _l == null) &&
		   (_u.getID() == _a.getOwnerID() && _l.getAppID() == _a.getID()))
		   {
	        response.setContentType("text/plain");
	        response.setHeader("Content-disposition", "attachment; filename=keys.csv");
			List<Key> _k = KeyDAO.getKeys(_l, false);
			
			StringBuilder sb = new StringBuilder();
			OutputStream outputStream = response.getOutputStream();
			
			for(Key k : _k)
			{
				sb.append(_l.getName() + "," + k.getLicenseKey()+ "\n");
			}
			
			outputStream.write(sb.toString().getBytes());
			outputStream.flush();
	        outputStream.close();
			
		   } else { response.sendRedirect("/user"); }
	}
}
