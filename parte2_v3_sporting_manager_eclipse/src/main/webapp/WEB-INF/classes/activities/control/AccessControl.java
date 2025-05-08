package activities.control;


import activities.db.*;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class AccessControl {

    String login;
    DBInteraction db;
    private static final String MANAGER_PASSWORD = "manager";

    public AccessControl(DBInteraction dbparam) throws Exception {
        db = dbparam;
    }

    public String getlogin() {
        return login;
    }

    // This method returns 'true' if the password of the manager is correct
    // or if the pair login/password of the client is correct
    public boolean authentication(String option, String testLogin, String testString) throws Exception {

        boolean auth = false;

        // Creates an MessageDigest object for computing password hashes
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Creates an MessageDigest object for using utilities
        Util util = new Util();

        // In case the introduced option is for the manager
        if (option.equals("A")) {
            // Read the password
            String pwd = testString;
            // Check if the password is the one of the manager (configured in the source code)
            if (pwd.equals(MANAGER_PASSWORD)) {
                return true;
            } else {
                return false;
            }
        } // In case the introduced option is for the client
        else if (option.equals("B")) {

            // Read the login
            login = testLogin;
            // Read the password
            String rawpwd = testString;
            md.update(rawpwd.getBytes(StandardCharsets.UTF_8));
            String pwd = util.bytesToHex(md.digest());
            // Interact with the database to know if the pair login/password exists in the CLIENTS table
            auth = db.authentication(login, pwd);
        }
        return auth;
    }
}

