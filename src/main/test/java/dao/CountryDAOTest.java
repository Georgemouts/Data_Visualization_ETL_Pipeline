package main.test.java.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.dao.CountryDAO;
import main.java.dao.CountryDAOFactory;
import main.java.dao.Names;

class CountryDAOTest {
	
	CountryDAO countryDAO = CountryDAOFactory.getCountryDAO("mysql");
	
	Names names_object = Names.getInstance();		
	
	List<String> countries = names_object.getCountries();
	
	@Test
	void testCountryDAOMySQLImplIsNotNull() {
		Assertions.assertNotNull(countryDAO);
	}
	
	
	@Test
	void testCountryNames() {
		Assertions.assertEquals(25, countries.size());
		Assertions.assertEquals("Albania", countries.get(0));
		Assertions.assertEquals("Andorra", countries.get(1));
		Assertions.assertEquals("Austria", countries.get(2));
		Assertions.assertNotEquals("Germany", countries.get(16));
		Assertions.assertEquals("Greece", countries.get(16));
		Assertions.assertEquals("Kosovo", countries.get(24));
	}
	
	@Test
	void testReadCountryIdFromName() {
		Assertions.assertEquals(25, countryDAO.readCountryIdFromName(countries).size());
		Assertions.assertNotEquals(24, countryDAO.readCountryIdFromName(countries).size());
		
		Assertions.assertEquals(1, countryDAO.readCountryIdFromName(countries).get(0));
		Assertions.assertEquals(17, countryDAO.readCountryIdFromName(countries).get(16));
	}
	
	

	

	
	
	
}
