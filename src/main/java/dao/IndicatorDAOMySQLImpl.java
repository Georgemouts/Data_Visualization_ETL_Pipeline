package main.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndicatorDAOMySQLImpl implements IndicatorDAO {
	private Connector connection;
	private ResultSet queryResult;
	private List<Integer> indicatorIds;
	private List<String> indicatorNames;
	
	public IndicatorDAOMySQLImpl() {
		this.connection = new Connector();	// TODO: maybe can me prototyped.
		this.indicatorIds = new ArrayList<Integer>();
	}
	
	@Override
	public List<Integer> readIndicatorIdFromName(List<String> names) {
		
		for (String name: names) {
			String query = "SELECT id FROM indicator WHERE name  = '" + name + "';";
			int id = 0;
			try {
				connection.connectWithDB();
				queryResult = connection.executeQuery(query);
				if (queryResult.next()) {
					id = queryResult.getInt("id");
					this.indicatorIds.add(id);
				} 
			
				}catch (SQLException ex) {
					connection.perrSQL(ex);
				}	
		}
		return this.indicatorIds;
	}
	
	
	
	@Override
	public List<String> readIndicatorNameFromId(List<Integer> ids) {

		for (Integer id: ids) {
			String query = "SELECT name FROM indicator WHERE id  = '" + id + "';";
			String name;
	
			try {
				connection.connectWithDB();
				queryResult = connection.executeQuery(query);
				if (queryResult.next()) {
					name = queryResult.getString("id");
					this.indicatorNames.add(name);
				} 
			
				}catch (SQLException ex) {
					connection.perrSQL(ex);
				}	
		}
		return this.indicatorNames;
	}
	
	@Override
	public String readSingleIndicatorNameFromId(int id) {
		String query = "SELECT name FROM indicator WHERE id  = " + id + ";";
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
