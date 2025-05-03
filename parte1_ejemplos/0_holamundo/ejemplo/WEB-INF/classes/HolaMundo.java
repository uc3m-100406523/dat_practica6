import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HolaMundo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
	// establece ContentType y sistema de codificacion
        response.setContentType("text/html; charset=ISO-8859-1");

	// obtiene un PrintWriter para escribir la respuesta
        PrintWriter out = response.getWriter();

	// escribe un documento XHTML
        out.println("<?xml version='1.0' encoding='ISO-8859-1' ?>");
        out.println("<!DOCTYPE html");
        out.println("          PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"");
        out.println("\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\" >");
	out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
	out.println("<head><title>&iexcl;Hola Mundo!</title></head>");

        out.println("<body>");
        out.println("<h1>&iexcl;Hola Mundo!</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
