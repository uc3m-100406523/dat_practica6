package activities.db;

import activities.db.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DBInteraction {
    Query q;
    Connection con;

    // Constructor that connects to the Data Base

    public DBInteraction () throws SQLException {
	    String url="jdbc:mysql://localhost/sporting_manager";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            System.out.println("Trying to connect...");
            con = DriverManager.getConnection (url, "root", "datuc3m");
            System.out.println("Connected!");
		}
		catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }
        q=new Query(con);
	}    

    //method to close the Statement and the Connection objects
    
	public void close()throws Exception{
        q.close();
		con.close();
	}

    // Method to add a new user to the CLIENTS table
    
	public void addusr(String login, String pwd, String name, String surname, String address, String phone)throws Exception{
        String addusr="INSERT INTO CLIENTS VALUES ('"+login+"','"+pwd+"','"+name+"','"+surname+"','"+address+"','"+phone+"')";
	    q.doUpdate(addusr);
	}
	/* */
	/*
	public void addusr(String login, String pwd, String name, String surname, String address, String phone) {
        // Verificación de que ninguno de los campos esté vacío o sea null
        if (login == null || pwd == null || name == null || surname == null || address == null || phone == null) {
            System.err.println("Error: All fields must be provided.");
            return; // Detener el proceso si hay campos nulos
        }

        // Verificación de que ninguno de los campos esté vacío
        if (login.isEmpty() || pwd.isEmpty() || name.isEmpty() || surname.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            System.err.println("Error: No field can be empty.");
            return; // Detener el proceso si algún campo está vacío
        }

        // Escapar los campos de entrada para prevenir vulnerabilidades XSS
        login = StringEscapeUtils.escapeHtml4(login);
        pwd = StringEscapeUtils.escapeHtml4(pwd);
        name = StringEscapeUtils.escapeHtml4(name);
        surname = StringEscapeUtils.escapeHtml4(surname);
        address = StringEscapeUtils.escapeHtml4(address);
        phone = StringEscapeUtils.escapeHtml4(phone);

        // Ejecutar la consulta SQL con PreparedStatement
        String sql = "INSERT INTO CLIENTS (LOGIN, PASSWORD, NAME, SURNAME, ADDRESS, PHONE) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, pwd);
            stmt.setString(3, name);
            stmt.setString(4, surname);
            stmt.setString(5, address);
            stmt.setString(6, phone);
            stmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }
	/* */

    // This method returns 'true' in case there exist a row in the CLIENTS table with the login and password passed as parameters
	// If there is not exist a row with such login and password, then it returns 'false'
	/* */
	public boolean authentication(String login, String pwd)throws Exception{
		String list="SELECT * FROM CLIENTS WHERE LOGIN='"+login+"'";
		String password=null;
		ResultSet rs=q.doSelect(list); //rs will contain the row with login passed as parameter
		if (rs.next()){ //Check if the Resultset is empty
		    password = rs.getString(2);
		}
		if (password == null){
			return(false);
		}
		if(password.equals(pwd)){ // In case the password for this login in the table is the same as the one passed as parameter
			return(true);
		}
		else {
			return(false);
		}
    }
	/* */
	/*
	public boolean authentication(String login, String pwd) {
        String sql = "SELECT PASSWORD FROM CLIENTS WHERE LOGIN = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return pwd.equals(rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return false;
    }
	/* */
	
	//This method delete a user from the CLIENTS table. This is the user with login passed as parameter
	/* */
	public void delusr(String login) throws Exception{
		String delusr="DELETE FROM CLIENTS WHERE LOGIN='"+login+"'";
		q.doUpdate(delusr);
	}
	/* */
	/*
	public void delusr(String login) {
        String sql = "DELETE FROM CLIENTS WHERE LOGIN = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
	/* */

    //This method adds a new activity in the ACTIVITIES table with the data passed as parameters
	/* */
	public void addact( String name, String description, String initial, float price, String pav_name, int total, int occ)throws Exception{
		String addactivity="INSERT INTO ACTIVITIES (NAME, DESCRIPTION, START_DATE, COST, PAVILLION_NAME, TOTAL_PLACES, OCCUPIED_PLACES) VALUES ('"+name+"','"+description+"','"+initial+"','"+price+"','"+pav_name+"','"+total+"','"+occ+"')";
		q.doUpdate(addactivity);
	}
	/* */
	/*
	public void addact(String name, String description, String initial, float price, String pav_name, int total, int occ) {
        // Escapar los campos de entrada para prevenir vulnerabilidades XSS
        name = StringEscapeUtils.escapeHtml4(name);
        description = StringEscapeUtils.escapeHtml4(description);
        initial = StringEscapeUtils.escapeHtml4(initial);
        pav_name = StringEscapeUtils.escapeHtml4(pav_name);

        String sql = "INSERT INTO ACTIVITIES (NAME, DESCRIPTION, START_DATE, COST, PAVILLION_NAME, TOTAL_PLACES, OCCUPIED_PLACES) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, initial);
            stmt.setFloat(4, price);
            stmt.setString(5, pav_name);
            stmt.setInt(6, total);
            stmt.setInt(7, occ);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding activity: " + e.getMessage());
        }
    }
	/* */
	
	//This method deletes a row in the ACTIVITIES table, that is the one that has the id passed as parameter
	/* */
	public void delact(int id) throws Exception{
		String delact="DELETE FROM ACTIVITIES WHERE ID='"+id+"'";
		q.doUpdate(delact);
	}
	/* */
	/*
	public void delact(int id) {
        String sql = "DELETE FROM ACTIVITIES WHERE ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting activity: " + e.getMessage());
        }
    }
	/* */

    //This method adds a new pavillion in the PAVILLIONS table with the data passed as parameters
	/* */
    public void addpav(String pavname, String pavlocation) throws Exception{
		String addpavillion="INSERT INTO PAVILLIONS VALUES ('"+pavname+"','"+pavlocation+"')";
		q.doUpdate(addpavillion);
	}
	/* */
	/*
	public void addpav(String pavname, String pavlocation) {
        // Escapar los campos de entrada para prevenir vulnerabilidades XSS
        pavname = StringEscapeUtils.escapeHtml4(pavname);
        pavlocation = StringEscapeUtils.escapeHtml4(pavlocation);

        String sql = "INSERT INTO PAVILLIONS (PAVILLON, LOCATION) VALUES (?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pavname);
            stmt.setString(2, pavlocation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding pavillion: " + e.getMessage());
        }
    }
	/* */

	//This method deletes a pavillion from the PAVILLIONS table. The deleted pavillion is the one with pavillion name passed as parameter
	/* */
	public void delpav(String pavname) throws Exception{
		String delpav="DELETE FROM PAVILLIONS WHERE PABELLON='"+pavname+"'";
		q.doUpdate(delpav);
	}
	/* */
	/*
	public void delpav(String pavname) {
        String sql = "DELETE FROM PAVILLIONS WHERE PAVILLON = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pavname);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting pavillion: " + e.getMessage());
        }
    }
	/* */

	//This method requests the execution of a SQL sentence for listing all the clients
	//and it retrieves all the information for each client, storing each client as an element
	//of an array. Each element contains an object of the type Client
	/* */
	public ArrayList listallusr() throws Exception{
		ArrayList data = new ArrayList();
		String selection="SELECT * FROM CLIENTS";
		ResultSet rs=q.doSelect(selection);
		while (rs.next()) {                     
		   String login = rs.getString(1);
		   String password = rs.getString(2);
           String name = rs.getString(3);
		   String surname = rs.getString(4);
           String address = rs.getString(5);
		   String phone = rs.getString(6);
           data.add(new Client(login, password, name, surname, address, phone));
	    }
		return (data);
	}
	/* */
	/*
	public ArrayList<Client> listallusr() {
        ArrayList<Client> data = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTS";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                data.add(new Client(
                        rs.getString("LOGIN"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getString("ADDRESS"),
                        rs.getString("PHONE")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error listing users: " + e.getMessage());
        }
        return data;
    }
	/* */

	//This method requests the execution of a SQL sentence for listing activities depending on some criterion
	//This method is common to all the listing activities operations
	//and it retrieves all the information for each activity, storing each activity as an element
	//of an array. Each element contains an object of the type Activity
	/* */
    public ArrayList listactivities(String selection) throws Exception{
		ArrayList data = new ArrayList();
		ResultSet rs=q.doSelect(selection);
		while (rs.next()) {                     
		    int id = rs.getInt(1);
			String name = rs.getString(2);
			String description = rs.getString(3);
			String initial = rs.getString(4);
			float cost = rs.getFloat(5);
			String pavname = rs.getString(6);
			int total = rs.getInt(7);
			int occupied = rs.getInt(8);
			data.add(new Activity (id, name, description, initial, cost, pavname, total, occupied));
		}
		return (data);
	}
	/* */
	/* */
	/* */

	// This method conforms a SQL sentence for listing all the activities
	/* */
	public ArrayList listallact() throws Exception{
		String selection="SELECT * FROM ACTIVITIES";
		ArrayList data = this.listactivities(selection);
		return (data);
	}
	/* */
	/*
	public ArrayList<Activity> listallact() {
        String sql = "SELECT * FROM ACTIVITIES";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            return listactivitiesFromQuery(stmt);
        } catch (SQLException e) {
            System.err.println("Error listing all activities: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	/* */

    // This method conforms a SQL sentence for listing the activities that have free places
	/* */
	public ArrayList listactfreeplaces() throws Exception{
		String selection="SELECT * FROM ACTIVITIES WHERE ACTIVITIES.TOTAL_PLACES > ACTIVITIES.OCCUPIED_PLACES";
		ArrayList data = this.listactivities(selection);
		return (data);
	}
	/* */
	/*
	public ArrayList<Activity> listactfreeplaces() {
        String sql = "SELECT * FROM ACTIVITIES WHERE TOTAL_PLACES > OCCUPIED_PLACES";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            return listactivitiesFromQuery(stmt);
        } catch (SQLException e) {
            System.err.println("Error listing free-place activities: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	/* */

	// This method conforms a SQL sentence for listing the activities which have less cost that certain amount
	/* */
	public ArrayList listactprice(Float price) throws Exception{
		String selection="SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.COST <="+price+"AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data); 
	}
	/* */
	/*
	public ArrayList<Activity> listactprice(float price) {
        String sql = "SELECT * FROM ACTIVITIES WHERE COST <= ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setFloat(1, price);
            return listactivitiesFromQuery(stmt);
        } catch (SQLException e) {
            System.err.println("Error listing activities by price: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	/* */

	// This method conforms a SQL sentence for listing the activities that take place in a certain pavillion

	public ArrayList listactpav(String namepav) throws Exception{
		String selection="SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.PAVILLION_NAME='"+namepav+"'AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data);
 	}
	/* */
	/*
	public ArrayList<Activity> listactpav(String namepav) {
        String sql = "SELECT * FROM ACTIVITIES WHERE PAVILLION_NAME = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, namepav);
            return listactivitiesFromQuery(stmt);
        } catch (SQLException e) {
            System.err.println("Error listing activities by pavillion: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	/* */

	// This method conforms a SQL sentence for listing the activities that have a specific name

	public ArrayList listactname(String nameact) throws Exception{
		String selection="SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.NAME='"+nameact+"'AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data);
  	}
	/* */
	/*
	public ArrayList<Activity> listactname(String nameact) {
        String sql = "SELECT * FROM ACTIVITIES WHERE NAME = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nameact);
            return listactivitiesFromQuery(stmt);
        } catch (SQLException e) {
            System.err.println("Error listing activities by name: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	/* */

	// This method conforms a SQL sentence for listing the activities in which a specific client is registered

	public ArrayList listactusr(String login) throws Exception{
		String selection="SELECT ID, NAME, DESCRIPTION, START_DATE, COST, PAVILLION_NAME, TOTAL_PLACES, OCCUPIED_PLACES FROM SUBSCRIPTIONS, ACTIVITIES, PAVILLIONS WHERE SUBSCRIPTIONS.CLIENT_LOGIN='"+login+"' AND SUBSCRIPTIONS.ACTIVITY_ID = ACTIVITIES.ID AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data);
   	}
	/* */
	/*
	public ArrayList<Activity> listactusr(String login) {
        String sql = "SELECT A.ID, A.NAME, A.DESCRIPTION, A.START_DATE, A.COST, A.PAVILLION_NAME, A.TOTAL_PLACES, A.OCCUPIED_PLACES " +
                     "FROM SUBSCRIPTIONS S JOIN ACTIVITIES A ON S.ACTIVITY_ID = A.ID " +
                     "WHERE S.CLIENT_LOGIN = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            return listactivitiesFromQuery(stmt);
        } catch (SQLException e) {
            System.err.println("Error listing user's activities: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	/* */

	// Method to retrieve activities between two dates
    public ArrayList listactbydates(String startDate, String endDate) throws Exception {
        String selection = "SELECT * FROM ACTIVITIES WHERE START_DATE BETWEEN '" + startDate + "' AND '" + endDate + "'";
        ArrayList data = this.listactivities(selection);
        return data;
    }
	/* */
	/*
	public ArrayList<Activity> listactbydates(String startDate, String endDate) {
        String sql = "SELECT * FROM ACTIVITIES WHERE START_DATE BETWEEN ? AND ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            return listactivitiesFromQuery(stmt);
        } catch (SQLException e) {
            System.err.println("Error listing activities by date: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	/* */

	//This method requests the execution of a SQL sentence for listing all the pavillions
	//and it retrieves all the information for each pavillion, storing each pavillion as an element
	//of an array. Each element contains an object of the type pavillion

	public ArrayList listallpav() throws Exception{
		ArrayList data = new ArrayList();
		String selection="SELECT * FROM PAVILLIONS";
		ResultSet rs=q.doSelect(selection);
		while (rs.next()) {                     
		    String name = rs.getString(1);
			String location = rs.getString(2);
			data.add(new Pavillion(name, location));
       }
	   return (data);
	}
	/* */
	/*
	public ArrayList<Pavillion> listallpav() {
        ArrayList<Pavillion> data = new ArrayList<>();
        String sql = "SELECT * FROM PAVILLIONS";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                data.add(new Pavillion(rs.getString("PAVILLON"), rs.getString("LOCATION")));
            }
        } catch (SQLException e) {
            System.err.println("Error listing pavillions: " + e.getMessage());
        }
        return data;
    }
	/* */

	//This method registers a client for a specific activity

    public void regactivity(String login, String id) throws Exception{
		String regactivity="INSERT INTO SUBSCRIPTIONS VALUES ('"+login+"','"+id+"')";
		q.doUpdate(regactivity);
	}
	/* */
	/*
	public void regactivity(String login, String id) {
        String sql = "INSERT INTO SUBSCRIPTIONS (CLIENT_LOGIN, ACTIVITY_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error registering activity: " + e.getMessage());
        }
    }
	/* */

	//This method unregisters a client from a specific activity

	public void unregactivity(String login, String id) throws Exception{
		String unregactivity="DELETE FROM SUBSCRIPTIONS WHERE SUBSCRIPTIONS.CLIENT_LOGIN='"+login+"'AND SUBSCRIPTIONS.ACTIVITY_ID="+id;
		q.doUpdate(unregactivity);
	}
	/* */
	/*
	public void unregactivity(String login, String id) {
        String sql = "DELETE FROM SUBSCRIPTIONS WHERE CLIENT_LOGIN = ? AND ACTIVITY_ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error unregistering activity: " + e.getMessage());
        }
    }
	/* */
}