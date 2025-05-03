import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Calendar.*;


public class time extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        Date clock = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(clock);
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DATE);
		int month = calendar.get(Calendar.MONTH);
		int hour = calendar.get(Calendar.HOUR);
		int minutes = calendar.get(Calendar.MINUTE);
		PrintWriter out = response.getWriter ();
        out.println("The present date and hour on the time server is");
        out.println("<p>");
        out.println("YEAR:"+year+" DAY:"+day+" MONTH:"+month+" HOUR:"+hour+" MINUTES:"+minutes);
    }
       
    public void doPost(HttpServletRequest request, HttpServletResponse res)
    throws IOException, ServletException
    {
		doGet(request, res);
	}
}
