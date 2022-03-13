package my030;

import java.sql.*;

public class Connector {
	static Connection connect_me = null;
	
	public static void executeQuery(String query) {
		ResultSet rs = null;
		Statement stmt = null;

        try {
        	stmt = connect_me.createStatement();  //execute querry 
        	rs = stmt.executeQuery(query); // store querry's result
        	System.out.println ("\n-------------SQL DATA-------------\n");
        	while(rs.next()) {   
        		String fname = rs.getString("first_name");
           	}
        	
        	System.out.println ("\n\n-------------END-------------\n");
        }
        catch (SQLException ex){
        	// handle any errors
        	System.out.println("SQLException: " + ex.getMessage());
        	System.out.println("SQLState: " + ex.getSQLState());
        	System.out.println("VendorError: " + ex.getErrorCode());
       }
        finally {
        	if (rs != null) {
        		try {
        			rs.close();
        		} catch (SQLException sqlEx) { } // ignore

        	}
        }
	}
        
	
	
	public static void main(String[] args) { 
	
		try {
			String userName = "root";
            String password = "1961999";
            String url = "jdbc:mysql://localhost/mye030?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";        
            connect_me = DriverManager.getConnection (url, userName, password);
            executeQuery("SELECT value FROM indicates WHERE year = 2020;");
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
}
