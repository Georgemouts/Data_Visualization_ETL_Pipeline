package main.java.dao;

// e.g. CountryDAO countryDAO = CountryDAOFactory.getCountryDAO("mysql");

public class CountryDAOFactory{
	
	public static CountryDAO getCountryDao(String dbName) throws IllegalArgumentException { 
		
		if (dbName == null || dbName.isEmpty())
	        return null;
		
	    if (dbName.equalsIgnoreCase("mysql"))
	        return new CountryDAOMySQLImpl();
	    
	    else 
	    	throw new IllegalArgumentException();  
	}
}
