package main.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndicatorDAOMySQLImpl implements IndicatorDAO {
	private Connector connection;
	private ResultSet queryResult;
	private List<Integer> indicatorIds;
	
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
