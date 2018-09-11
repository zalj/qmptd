package cacu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cacu.DataBaseOperator;

/**
 * Servlet implementation class DisplayDelayServlet
 */
@WebServlet("/DisplayDelayServlet")
public class DisplayDelayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static int last = 1;
       
    public DisplayDelayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		/*for(int i = 50; i > 0; i /= 3) {
			if(DataBaseOperator.getInstance().find(last + i)[0] != null) {
				last = last + i;
				break;
			}
		}*/
		String[][] temp = DataBaseOperator.getInstance().findLatestNRecord(1);
		response.setIntHeader("Refresh", 1);
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("	<HEAD><meta charset=\"UTF-8\"><TITLE>" + "ShowByTable" +"</TITLE></HEAD>");
		out.println("	<BODY>");
		out.println("		<TABLE border = \"1\">");
		out.println("<tr>");
		out.println("<th>Last Time Delay</th>");
		out.println("<td>" + temp[0][0] + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>Average Time Delay</th>");
		out.println("<td>" + temp[0][1] + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>Good Jitter</th>");
		out.println("<td>" + temp[0][2] + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>Bad Jitter</th>");
		out.println("<td>" + temp[0][3] + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>Missing Rate</th>");
		out.println("<td>" + temp[0][5] + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>Throughput Capacity</th>");
		out.println("<td>" + temp[0][6] + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>Missing Count</th>");
		out.println("<td>" + temp[0][4] + "</td>");
		out.println("</tr>");
		out.print(CalcuTest.getTime());
		out.println("		</TABLE>");
		out.println("	</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
