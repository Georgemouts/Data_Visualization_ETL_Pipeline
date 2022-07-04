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
		this.connection = Connector.getInstance();	
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
	
	
	public Connector getConnection() {
		return connection;
	}

	public ResultSet getQueryResult() {
		return queryResult;
	}

	public List<Integer> getCountryIds() {
		return countryIds;
	}

	public List<String> getCountryNames() {
		return countryNames;
	}

	public void setConnection(Connector connection) {
		this.connection = connection;
	}

	public void setQueryResult(ResultSet queryResult) {
		this.queryResult = queryResult;
	}

	public void setCountryIds(List<Integer> countryIds) {
		this.countryIds = countryIds;
	}

	public void setCountryNames(List<String> countryNames) {
		this.countryNames = countryNames;
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
