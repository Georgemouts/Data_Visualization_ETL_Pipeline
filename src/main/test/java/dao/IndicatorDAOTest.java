package main.test.java.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.dao.IndicatorDAO;
import main.java.dao.IndicatorDAOFactory;
import main.java.dao.Names;

public class IndicatorDAOTest {
	IndicatorDAO indicatorDAO = IndicatorDAOFactory.getIndicatorDAO("mysql");
	
	Names names_object = Names.getInstance();		
	
	List<String> indicators = new ArrayList<String>();

	@Test
	void testIndicatorDAOMySQLImplIsNotNull() {
		Assertions.assertNotNull(indicatorDAO);
	}
	
	@Test
	void testIndicatorNamesAndIndicatorIdFromName() {
		names_object.getNY_indic().addAll(names_object.getSE_indic());
		indicators = names_object.getNY_indic();

		
		Assertions.assertNotNull(indicators);
		
		Assertions.assertEquals(217, indicators.size());
		Assertions.assertNotEquals(216, indicators.size());
		Assertions.assertEquals("Net primary income (Net income from abroad) (current LCU)", indicators.get(0));
		Assertions.assertEquals("GNI growth (annual %)", indicators.get(1));
		Assertions.assertEquals("Adjusted savings: net forest depletion (% of GNI)", indicators.get(5));
		Assertions.assertEquals("Coal rents (% of GDP)", indicators.get(10));
		Assertions.assertEquals("School enrollment, preprimary, female (% gross)", indicators.get(216));
		
		
		Assertions.assertEquals(217, indicatorDAO.readIndicatorIdFromName(indicators).size());
		Assertions.assertNotEquals(216, indicatorDAO.readIndicatorIdFromName(indicators).size());
		
		Assertions.assertEquals(10, indicatorDAO.readIndicatorIdFromName(indicators).get(0));
		Assertions.assertEquals(12, indicatorDAO.readIndicatorIdFromName(indicators).get(2));
	}
	

	
}
