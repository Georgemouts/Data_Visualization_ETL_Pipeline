package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Connector {
	private Connection connect_me;

	public Connector() {
		this.connect_me = null;
	}
	

	public void test(List<List<String>> listOfLists) {
		PreprocessQuery prepr = new PreprocessQuery();
		// List<String> value = new ArrayList<String>(Arrays.asList("value"));
		prepr.createSelect_FromPrefix(Arrays.asList("value"), Arrays.asList("indicates"));
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		Statement stmt = null;

        try {
        	stmt = connect_me.createStatement();
        	rs = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
        	perrSQL(ex);
        }
   
        finally {
        	if (rs != null) {
        		try {
        			rs.close();
        		} catch (SQLException sqlEx) { } // ignore

        	}
        }
        
        return rs;
	}
        
	
	public void connectWithDB() { 
		try {
			String userName = "root";
            String password = "christos";
            String url = "jdbc:MySQL://localhost/mye030";        
            this.connect_me = DriverManager.getConnection (url, userName, password);
		} catch (SQLException ex) {
			// handle any errors
			perrSQL(ex);
		}
	}
	
	public void perrSQL(SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
    	System.out.println("SQLState: " + ex.getSQLState());
    	System.out.println("VendorError: " + ex.getErrorCode());
	}
	
}