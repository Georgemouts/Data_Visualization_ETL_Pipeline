package main.test.java.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.dao.CountryDAO;
import main.java.dao.CountryDAOFactory;
import main.java.dao.IndicatorDAO;
import main.java.dao.IndicatorDAOFactory;
import main.java.dao.Names;
import main.java.dao.ValueFromCountryAndIndicatorDAO;
import main.java.dao.ValueFromCountryAndIndicatorDAOFactory;

public class ValueFromCountryAndIndicatorDAOTest {
	
	ValueFromCountryAndIndicatorDAO valueFromCountryAndIndicatorDAO = ValueFromCountryAndIndicatorDAOFactory.getValueFromCountryAndIndicatorDAO("mysql");
	
	
	CountryDAO country_table_accessor_obj = CountryDAOFactory.getCountryDAO("mysql");
	IndicatorDAO indicator_table_accessor_obj = IndicatorDAOFactory.getIndicatorDAO("mysql");
	
	Names names_object = Names.getInstance();		
	
	List<String> indicators = new ArrayList<String>();
	List<String> countries = names_object.getCountries();
	
	Map<List<String>, Long> valueFromCountryAndIndicator;
	
	@Test
	void testValueFromCountryAndIndicatorDAOMySQLImplIsNotNull() {
		Assertions.assertNotNull(valueFromCountryAndIndicatorDAO);
	}
	
	
	@Test
	void testValueFromCountryAndIndicatorNames() {
		
		names_object.getNY_indic().addAll(names_object.getSE_indic());
		indicators = names_object.getNY_indic();
		
		
		Assertions.assertNotNull(indicators);
		
		
		List<Integer> cids = country_table_accessor_obj.readCountryIdFromName(countries.subList(0, 3));
		List<Integer> iids = indicator_table_accessor_obj.readIndicatorIdFromName(indicators.subList(0, 3));
		List<Integer> years = new ArrayList<Integer>();
		
		for (String year: names_object.getYears())
			 years.add(Integer.parseInt(year));
		
		valueFromCountryAndIndicator = valueFromCountryAndIndicatorDAO.readValueFromCountryAndIndicator(cids, iids, years);
		
		Assertions.assertNotNull(valueFromCountryAndIndicator);
		
		Assertions.assertEquals(549, valueFromCountryAndIndicator.size());
		
		List<List> keys = new ArrayList<List>(valueFromCountryAndIndicator.keySet());
		List<Long> values = new ArrayList<Long>(valueFromCountryAndIndicator.values());
		
		
		Assertions.assertEquals("Austria", keys.get(0).get(0));
		Assertions.assertEquals("GNI growth (annual %)", keys.get(0).get(1));
		Assertions.assertEquals("1972", keys.get(0).get(2));
		Assertions.assertEquals(0, values.get(0));
		
		Assertions.assertEquals("Austria", keys.get(4).get(0));
		Assertions.assertEquals("GDP per capita (constant 2015 US$)", keys.get(4).get(1));
		Assertions.assertEquals("1985", keys.get(4).get(2));
		Assertions.assertEquals(27384, values.get(4));
		
		
		Assertions.assertEquals("Andorra", keys.get(113).get(0));
		Assertions.assertEquals("GDP per capita (constant 2015 US$)", keys.get(113).get(1));
		Assertions.assertEquals("2019", keys.get(113).get(2));
		Assertions.assertEquals(39003, values.get(113));
	}
	
	
	
	
}
