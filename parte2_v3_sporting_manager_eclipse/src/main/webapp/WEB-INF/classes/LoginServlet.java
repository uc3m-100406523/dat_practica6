import java.io.*;
import java.security.MessageDigest;
import java.util.*;
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;

import activities.control.AccessControl;
import activities.db.*;

public class LoginServlet extends HttpServlet {

	// Contador
	private int count=0;

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        /* JSP de autenticación */
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String userLogin = req.getParameter("cliente_login");
		String userPassword = req.getParameter("cliente_pwd");

		PrintWriter out = res.getWriter();

		try{
			// Call for a reference for data base access
        	DBInteraction db = new DBInteraction();

			// Check the user selection: user, manager or registration as new user
			AccessControl ac = new AccessControl(db);

			// Creates an MessageDigest object for computing password hashes
        	//MessageDigest md = MessageDigest.getInstance("SHA-256");

        	// Creates an MessageDigest object for using utilities
        	//Util util = new Util();

			/* Se comprueba si las credenciales son válidas y se genera una respuesta */
			boolean auth = ac.authentication("B", userLogin, userPassword);
			if(auth) {
				RequestDispatcher end=req.getRequestDispatcher("search.html");
				end.include(req, res);
			}
			else {
				count++;

				if(count == 3) {
					RequestDispatcher end=req.getRequestDispatcher("index.html");
					end.include(req, res);
				}
				else {
					RequestDispatcher end=req.getRequestDispatcher("relogin.jsp");
					end.include(req, res);
				}
			}

			db.close();
		} //end try
		catch (Exception e){  }
	}//doPost end
}
