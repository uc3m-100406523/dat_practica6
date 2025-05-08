import java.io.*;
import java.util.*;
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;
import activities.db.*;

//import activities.model.Cliente;

public class GuardarCliente extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client cliente = (Client) session.getAttribute("cliente");
        try {
            DBInteraction db=new DBInteraction();
            if (cliente != null) {
                //ClienteDAO dao = new ClienteDAO();
                //dao.insertarCliente(cliente);
                
                //db.addusr(getServletInfo(), getServletInfo(), getServletName(), getServletInfo(), getServletName(), getServletInfo());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
        dispatcher.forward(request, response);
    }
}
