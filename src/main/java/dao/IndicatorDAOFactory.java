package main.java.dao;

// e.g. IndicatorDAO indicatorDAO = IndicatorDAOFactory.getIndicatorDAO("mysql");

public class IndicatorDAOFactory {
	
	public static IndicatorDAO getIndicatorDAO(String dbName) throws IllegalArgumentException { 
		
		if (dbName == null || dbName.isEmpty())
	        return null;
		
	    if (dbName.equalsIgnoreCase("mysql"))
	        return new IndicatorDAOMySQLImpl();
	    
	    else 
	    	throw new IllegalArgumentException();  
	}
}