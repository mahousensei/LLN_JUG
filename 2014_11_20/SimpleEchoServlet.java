package internet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SimpleEchoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Set response content type
	    resp.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = resp.getWriter();
      out.println("<h1>" + req.getRemoteAddr() + "</h1>");		
		
	}
}
