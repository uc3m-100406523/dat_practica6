package activities.control;


import activities.db.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Control {

    public static void main(String args[]) throws Exception {

        // Represents the option the user selects on the menu (A, B, or C are the valid ones)
        String option = "no";
        String login;

        boolean end = false;

        // Creates an InputOutput object for writing messages and reading from the keyboard
        InputOutput io = new InputOutput();

        // Call for a reference for data base access
        DBInteraction db = new DBInteraction();

        // Check the user selection: user, manager or registration as new user
        AccessControl ac = new AccessControl(db);

        // Creates an MessageDigest object for computing password hashes
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Creates an MessageDigest object for using utilities
        Util util = new Util();

        // A loop to be writing the initial menu until the selected option is A or B
        while (!((option.equals("A")) || (option.equals("B")))) {
            // Print the initial menu for the user
            io.writeinitialmenu();

            // Reads the option the user selects
            option = io.read();

            // Option C: register a new client
            if (option.equals("C")) {

                // Read from standard input all the data of a client
                io.writelogin();
                login = io.read();
                io.writepwd();
                String rawpwd = io.read();
                md.update(rawpwd.getBytes(StandardCharsets.UTF_8));
                String pwd = util.bytesToHex(md.digest());
                io.writename();
                String name = io.read();
                io.writesurname();
                String surname = io.read();
                io.writeadress();
                String address = io.read();
                io.writephone();
                String phone = io.read();
                // Insert a new client
                db.addusr(login, pwd, name, surname, address, phone);
            } else { // In case option is different to C
                boolean auth = ac.authentication(option);
                if (!auth) {
                    /*
                     * if it is not authenticated as manager or client,
                     * then option is set to "no" to be within the loop in order
                     * to print again the menu
                     */
                    option = "no";
                }
            }
        }

        // The manager or client has been authenticated correctly
        // Retrieve the login
        login = ac.getlogin();

        // ManagerActions Control
        if (option.equals("A")) {
            ManagerActions m = new ManagerActions(db);
            while (!end) {
                // Print the menu of the manager
                io.writeManager();
                // Read the selected option of the manager menu
                option = io.read();
                int a = Integer.parseInt(option);
                // Call the proper method of the ManagerActions object according to the selected option
                switch (a) {
                    case 1:
                        m.addusr();
                        break;
                    case 2:
                        m.delusr();
                        break;
                    case 3:
                        m.addact();
                        break;
                    case 4:
                        m.iteraddact();
                        break;
                    case 5:
                        m.delact();
                        break;
                    case 6:
                        m.addpav();
                        break;
                    case 7:
                        m.delpav();
                        break;
                    case 8:
                        m.listallusr();
                        break;
                    case 9:
                        m.listUsersWithMinActivities();
                        break;
                    case 10:
                        m.listallact();
                        break;
                    case 11:
                        m.listallpav();
                        break;
                    case 12:
                        m.listactfreeplaces();
                        break;
                    // Exit option end the loop
                    case 13:
                        end = true;
                        break;
                    // The selected option is not on the menu
                    default:
                        end = false;
                }
            }
        } else {
            // ClientActions Control
            ClientActions u = new ClientActions(db);
            while (!end) {
                // Print the initial menu for a client
                io.writeClient();
                // Read the selected option from the client menu
                option = io.read();
                int a = Integer.parseInt(option);
                // Call the proper method of the ClientActions object according to the selected option
                switch (a) {
                    case 1:
                        u.listallact();
                        break;
                    case 2:
                        u.listallpav();
                        break;
                    case 3:
                        u.listactfreeplaces();
                        break;
                    case 4:
                        u.listactprice();
                        break;
                    case 5:
                        u.listactpav();
                        break;
                    case 6:
                        u.listactbydates();
                        break;
                    case 7:
                        u.listactname();
                        break;
                    case 8:
                        u.listactusr(login);
                        break;
                    case 9:
                        u.regactivity(login);
                        break;
                    case 10:
                        u.unregactivity(login);
                        break;
                    case 11:
                        u.listUsersWithMinCommonActivities(login);
                        break;
                    // Exit option end the loop
                    case 12:
                        end = true;
                        break;
                    // The selected option is not on the menu
                    default:
                        end = false;
                }
            }
        }

        // Close all the resources related to the database
        db.close();

    }
} 


