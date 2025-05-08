import java.io.*;
import java.util.*;
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;

import activities.control.AccessControl;
import activities.db.*;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

//import activities.db.Cliente;

public class GuardarCliente extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("GET Request. No Form Data Posted");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Se recupera la confirmación */
        boolean confirm = Boolean.parseBoolean(request.getParameter("is_confirmed"));
        if (!confirm) {
            /* Si no se ha confirmado, se vuelve a la página de registro */
            RequestDispatcher dispatcher = request.getRequestDispatcher("/reregister.jsp");
            dispatcher.forward(request, response);
            return;
        }
        else {
            /* Si se ha confirmado, se guarda el cliente */
            
            /* Se recupera el cliente (guardado en la sesión) y se guarda en la BD */
            HttpSession session = request.getSession();
            Client cliente = (Client) session.getAttribute("cliente");
            try {
                // Call for a reference for data base access
                DBInteraction db = new DBInteraction();

                // Creates an MessageDigest object for computing password hashes
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                // Creates an MessageDigest object for using utilities
                Util util = new Util();

                String rawpwd = cliente.getpassword();
                md.update(rawpwd.getBytes(StandardCharsets.UTF_8));
                String pwd = util.bytesToHex(md.digest());

                if (cliente != null) {
                    db.addusr(cliente.getlogin(), pwd, cliente.getname(), cliente.getsurname(), cliente.getaddress(), cliente.getphone());
                }
                db.close();
            } catch (Exception e) {
                // TODO: handle exception
            }

            /* JSP de "Registro exitoso" */
            RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
            dispatcher.forward(request, response);
        }
    }
}
