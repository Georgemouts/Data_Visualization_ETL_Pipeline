package main.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// executes query to table Country
public class CountryDAOMySQLImpl implements CountryDAO {
	private Connector connection;
	private ResultSet queryResult;
	private List<Integer> countryIds;
	private List<String> countryNames;
	
	public CountryDAOMySQLImpl() {
		this.connection = new Connector();	// TODO: maybe can me prototyped.
		this.countryIds = new ArrayList<Integer>();
	}
	
	@Override
	public List<Integer> readCountryIdFromName(List<String> names) {
		
		for (String name: names) {
			String query = "SELECT id FROM country WHERE name  = '" + name + "';";
			int id = 0;
	
			try {
				connection.connectWithDB();
				queryResult = connection.executeQuery(query);
				if (queryResult.next()) {
					id = queryResult.getInt("id");
					this.countryIds.add(id);
				} 
			
				}catch (SQLException ex) {
					connection.perrSQL(ex);
				}finally {
					
					 try {
						 queryResult.close();
				        } catch (SQLException sqlEx) { } // ignore

					 queryResult = null;
				}
		}
			
		return this.countryIds;
	}
	
	
	
	@Override
	public List<String> readCountryNameFromId(List<Integer> ids) {

		for (Integer id: ids) {
			String query = "SELECT name FROM country WHERE country.id  = '" + id + "';";
			String name;
	
			try {
				connection.connectWithDB();
				queryResult = connection.executeQuery(query);
				if (queryResult.next()) {
					name = queryResult.getString("id");
					this.countryNames.add(name);
				} 
			
				}catch (SQLException ex) {
					connection.perrSQL(ex);
				}	
		}
		return this.countryNames;
	}
	
	
	@Override
	public String readSingleCountryNameFromId(int id) {
		String query = "SELECT name FROM country WHERE country.id = " + id + ";";
		String name = "";
	
		try {
			connection.connectWithDB();
			queryResult = connection.executeQuery(query);
			
			if (queryResult.next()) 
				name = queryResult.getString("name");
			
			
			}catch (SQLException ex) {
				connection.perrSQL(ex);
			}	
		return name;
	}
	
	
	
	

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void update(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(int id, String name, String code) {
		// TODO Auto-generated method stub

	}



}
