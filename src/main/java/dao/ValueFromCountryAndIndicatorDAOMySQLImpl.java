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
	private Map<List<String>, Long> indicatorYearlyValueForCountry;
	
	public ValueFromCountryAndIndicatorDAOMySQLImpl() {
		connection = new Connector();
		indicatorYearlyValueForCountry = new HashMap<List<String>, Long>();
	} 
	

	
	@Override
	public Map<List<String>, Long> readValueFromCountryAndIndicator(List<Integer> id_country, List<Integer> id_ind, List<Integer> years) {
		List<String> country_indicator_year = new ArrayList<String>(Arrays.asList("", "", ""));
		CountryDAOMySQLImpl country_dao_obj = new CountryDAOMySQLImpl();
		IndicatorDAOMySQLImpl indicator_dao_obj = new IndicatorDAOMySQLImpl();
		
 		for (Integer country: id_country) {
 			String country_name = country_dao_obj.readSingleCountryNameFromId(country);
 			country_indicator_year.set(0, country_name);
 			
			for (Integer indicator: id_ind) {
				String indicator_name = indicator_dao_obj.readSingleIndicatorNameFromId(indicator);
				country_indicator_year.set(1, indicator_name);
				
				for (Integer year: years) {
					country_indicator_year.set(2, Integer.toString(year));
					
					String query = "SELECT value FROM indicates WHERE id_country = " + country 
							+ " AND id_ind = " + indicator + " AND year = " + year + ";";
					//System.out.println(query);
					long value = 0;
					try {
						connection.connectWithDB();
						queryResult = connection.executeQuery(query);
						if (queryResult.next()) {
							value = queryResult.getLong("value");
							this.indicatorYearlyValueForCountry.put(country_indicator_year, value);
							// System.out.println(Arrays.toString(indicatorYearlyValueForCountry.entrySet().toArray()));
						} 
						
						}catch (SQLException ex) {
							connection.perrSQL(ex);
					 	}finally {
							
							 try {
								 queryResult.close();
						        } catch (SQLException sqlEx) { } // ignore

							 queryResult = null;
						}
					
					country_indicator_year = new ArrayList<String>(Arrays.asList(country_name, indicator_name, ""));
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
