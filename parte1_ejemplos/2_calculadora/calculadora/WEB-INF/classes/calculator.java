import java.io.*;
import java.util.*;
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class calculator extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("GET Request. No Form Data Posted");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse res)
    throws IOException, ServletException
    {
        PrintWriter out = res.getWriter ();
            int arg1 = Integer.parseInt(request.getParameter("arg1"));
			int arg2 = Integer.parseInt(request.getParameter("arg2"));
			String operacion = request.getParameter("operation");
            if (operacion.equals("add")) {
                int resultaux = arg1+arg2;
			    String result = String.valueOf(resultaux);
			    out.println("The result of the add operation is"+result);
	        }
		    else if (operacion.equals("substract")) {
                int resultaux = arg1-arg2;
			    String result = String.valueOf(resultaux);
			    out.println("The result of the substract operation is"+result);
	        }
		    else if (operacion.equals("multiplication")) {
                int resultaux = arg1*arg2;
			    String result = String.valueOf(resultaux);
			    out.println("The result of the multiplication is"+result);
	        }
		    else if (operacion.equals("division")) {
                int resultaux = arg1/arg2;
			    String result = String.valueOf(resultaux);
			    out.println("The result of the division is"+result);
	        }
			else{
		        out.println("The operation parameter that has been entered is incorrect. Please, enter add, substract, multiplication or division");
			}
    }
}
