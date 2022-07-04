package main.java.controller.charts;

import java.util.List;
import java.util.Map;

import main.java.controller.Controller;

public class PlotChartControllerFactory {
	
public static PlotChartController getPlotChartController(String chartType, Map<List<String>, Long>valueFromIndicatorYearCountryMap, 
		int yearFormat, int startingYear, int endingYear) throws IllegalArgumentException { 
		
		if (chartType == null || chartType.isEmpty())
	        return null;
		
	    if (chartType.equalsIgnoreCase("bar"))
	        return new PlotBarChartController(valueFromIndicatorYearCountryMap, yearFormat, startingYear, endingYear);
	    
	    if (chartType.equalsIgnoreCase("timeline"))
	        return new PlotTimelineChartController(valueFromIndicatorYearCountryMap, yearFormat, startingYear, endingYear);
	    
	    if (chartType.equalsIgnoreCase("scatter"))
	        return new PlotScatterChartController(valueFromIndicatorYearCountryMap, yearFormat, startingYear, endingYear);
	    
	    else 
	    	throw new IllegalArgumentException();  
	}
}
