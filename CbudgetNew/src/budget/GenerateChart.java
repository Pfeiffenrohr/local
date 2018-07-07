package budget;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GenerateChart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Hallo Welt");
		out.println("</body>");
		out.println("</html>");*/
		
		
		response.setContentType("image/png");
		HttpSession session = request.getSession(true);
		OutputStream outputStream = response.getOutputStream();
		Vector chartVec = (Vector)session.getAttribute("chart_vec");
		String mode=request.getParameter("mode");
		//createDataset(chartVec);
	}
}

	