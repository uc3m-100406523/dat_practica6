import java.io.*;
import java.util.*;
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;
import activities.db.*;

public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        /* JSP de registro */
        RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
        dispatcher.forward(req, res);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        /* Se crea el cliente y se guarda */
        String login = request.getParameter("cliente_login");
        String passwd = request.getParameter("cliente_pwd");
        String name = request.getParameter("cliente_name");
        String surname = request.getParameter("cliente_surname");
        String address = request.getParameter("cliente_address");
        String phone = request.getParameter("cliente_phone");
        Client cliente = new Client(login, passwd, name, surname, address, phone);

        /* El cliente se guarda en la sesión */
        session.setAttribute("cliente", cliente);

        /* Se guardan el resto de valores */
        session.setAttribute("cliente_login", login);
        session.setAttribute("cliente_name", name);
        session.setAttribute("cliente_surname", surname);
        session.setAttribute("cliente_address", address);
        session.setAttribute("cliente_phone", phone);

        /* Cookie */
        String sessionID = session.getId();
        int lifetime = 3600;
        Cookie sessionCookie = new Cookie("JSESSIONID", sessionID);
        sessionCookie.setMaxAge(lifetime);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        response.addCookie(sessionCookie);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/confirm.jsp");
        dispatcher.forward(request, response);
    }
}