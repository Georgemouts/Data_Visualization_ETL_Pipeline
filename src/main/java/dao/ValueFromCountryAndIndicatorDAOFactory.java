package main.java.dao;

public class ValueFromCountryAndIndicatorDAOFactory {
	
	public static ValueFromCountryAndIndicatorDAO getValueFromCountryAndIndicatorDAO(String dbName) throws IllegalArgumentException { 
		
		if (dbName == null || dbName.isEmpty())
	        return null;
		
	    if (dbName.equalsIgnoreCase("mysql"))
	        return new ValueFromCountryAndIndicatorDAOMySQLImpl();
	    
	    else 
	    	throw new IllegalArgumentException();  
	}
}

