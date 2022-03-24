package main.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueFromCountryAndIndicatorDAOMySQLImpl implements ValueFromCountryAndIndicatorDAO {
	private Connector connection;
	private ResultSet queryResult;
	private Map<List<Integer>, Double> indicatorYearlyValueForCountry;
	
	public ValueFromCountryAndIndicatorDAOMySQLImpl() {
		connection = new Connector();
		indicatorYearlyValueForCountry = new HashMap<List<Integer>, Double>();
	}
	
	@Override
	public Map<List<Integer>, Double> readValueFromCountryAndIndicator(List<Integer> id_country, List<Integer> id_ind, List<Integer> years) {
		List<Integer> country_indicator_year = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
		
 		for (Integer country: id_country) {
 			country_indicator_year.set(0, country);
			for (Integer indicator: id_ind) {
				country_indicator_year.set(1, indicator);
				for (Integer year: years) {
					country_indicator_year.set(2, year);
					String query = "SELECT value FROM indicates WHERE id_country = " + country 
							+ " AND id_ind = " + indicator + " AND year = " + year + ";";
					//System.out.println(query);
					double value = 0;
					try {
						connection.connectWithDB();
						queryResult = connection.executeQuery(query);
						if (queryResult.next()) {
							value = queryResult.getDouble("value");
							System.out.println(country_indicator_year.get(0) + " " + country_indicator_year.get(1) + " " + 
							country_indicator_year.get(2) + " " + value);
							this.indicatorYearlyValueForCountry.put(country_indicator_year, value);
						} 
						
						}catch (SQLException ex) {
							connection.perrSQL(ex);
					 	}
				}
			}
		}
 		return this.indicatorYearlyValueForCountry;
	}

	@Override
	public void delete(int id_country, int id_ind, int year) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int id_country, int id_ind, int year) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(int id_country, int id_ind, int year, int value) {
		// TODO Auto-generated method stub

	}

}
