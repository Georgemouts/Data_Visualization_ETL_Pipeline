package main.java.model;

import java.util.ArrayList;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class PreprocessScatterchartComponents {
	private Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	
	public PreprocessScatterchartComponents(Map<List<String>, Long> valueFromIndicatorYearCountryMap) {
		this.valueFromIndicatorYearCountryMap = valueFromIndicatorYearCountryMap;
	}
	
	
	// TODO: move it to main.java.model 
		public void addSeriesToScatterchartLegend(ScatterChart scatterChart) {
			List<List<String>> country_indicatorPairList = getCountry_IndicatorPairs(filterListFromYears());
	        System.gc();
	        
	        
	        for (List<String> pair: country_indicatorPairList) {
	        	
	        	XYChart.Series series = new XYChart.Series();
	        	
	        	series.setName(pair.get(0) + " " + pair.get(1));
	        	
	        	for (Map.Entry<List<String>, Long> entry : valueFromIndicatorYearCountryMap.entrySet()) {
	        		
	        	    List<String> keyList = entry.getKey();
	        	    
	        	    if (!keyList.get(0).equals(pair.get(0)) || !keyList.get(1).equals(pair.get(1)))
	        	    	continue;
	        	  
	        	    long value = entry.getValue();
	        	    
	        	    
	        	    series.getData().add(new XYChart.Data(Integer.parseInt(keyList.get(2)), value));
	        	    
	    		}
	        	scatterChart.getData().add(series);
	        }		
		}
		
		// TODO: move it to main.java.model 
		public List<List<String>> getCountry_IndicatorPairs(List<List<String>> country_indicatorPairList) {
			Set<List<String>> set = new LinkedHashSet<>();
			  
	        // Add the elements to set
	        set.addAll(country_indicatorPairList);
	  
	        // Clear the list
	        country_indicatorPairList.clear();
	  
	        // add the elements of set
	        // with no duplicates to the list
	        country_indicatorPairList.addAll(set);
	        
	        return country_indicatorPairList;
		}	
		
		/**
		 * @return :  list with country_indicator pairs.
		 */
		
		
		
		
		public List<List<String>> filterListFromYears() {
			List<List<String>> country_indicator_yearList = new ArrayList<List<String>>();
			
			// Store country name and indicator name into country_indicator_yearList.
			for (Map.Entry<List<String>, Long> entry : valueFromIndicatorYearCountryMap.entrySet()) {
	    	    List<String> keyList = entry.getKey();
	    	    
	    	    // country name at index 0, indicator name at index 1.
	    	    country_indicator_yearList.add(keyList.subList(0, 2));
			}
			return country_indicator_yearList;
		}
}
