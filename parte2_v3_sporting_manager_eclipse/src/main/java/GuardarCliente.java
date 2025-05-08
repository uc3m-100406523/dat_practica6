import java.io.*;
import java.util.*;
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;
import activities.db.*;

//import activities.model.Cliente;

public class GuardarCliente extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("GET Request. No Form Data Posted");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client cliente = (Client) session.getAttribute("cliente");
        try {
            DBInteraction db=new DBInteraction();
            if (cliente != null) {
                //ClienteDAO dao = new ClienteDAO();
                //dao.insertarCliente(cliente);
                
                db.addusr(cliente.getlogin(), cliente.getpassword(), cliente.getname(), cliente.getsurname(), cliente.getaddress(), cliente.getphone());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
        dispatcher.forward(request, response);
    }
}
