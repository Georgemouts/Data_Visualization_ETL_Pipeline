package main.java.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class PreprocessBarchartComponents {
	private Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	
	public PreprocessBarchartComponents(Map<List<String>, Long> valueFromIndicatorYearCountryMap) {
		this.valueFromIndicatorYearCountryMap = valueFromIndicatorYearCountryMap;
	}
	
	
	// TODO: move it to main.java.model 
		public void addSeriesToBarchartLegend(int startingYear, int endingYear, BarChart barChart) {
			List<List<String>> country_indicatorPairList = getCountry_IndicatorPairs(filterListFromYears());
	        System.gc();
	        
	        List<Integer> yearList = createYearList();
	        
	        List<String> country_indicatorSeries = new ArrayList<String>();
	        
	        	
	        for (List<String> pair: country_indicatorPairList) {
	        	
	        	XYChart.Series series = new XYChart.Series();
	        	List<List<String>> seriesPairs = new ArrayList<List<String>>();
	        	
	        	series.setName(pair.get(0) + " " + pair.get(1));
	        	
	        	for (Map.Entry<List<String>, Long> entry : valueFromIndicatorYearCountryMap.entrySet()) {
	        		
	        		List<String> keyList = entry.getKey();
	        		
	        		if ((Integer.parseInt(keyList.get(2)) < startingYear) || (Integer.parseInt(keyList.get(2)) > endingYear))
	        			continue;
	        	
	        	
	        		if (!keyList.get(0).equals(pair.get(0)) || !keyList.get(1).equals(pair.get(1)))
	        			continue;
	        			
	        			
	        		long value = entry.getValue();
	        	    
	        		// int year = yearList.get(yearList.indexOf(Integer.parseInt(keyList.get(2))));
	        			
	        		seriesPairs.add(Arrays.asList(keyList.get(2), Long.toString(value)));
	        		
	        	}
	        	
	        	seriesPairs.sort(Comparator.comparing(l -> Integer.parseInt(l.get(0))));
	        	
	        	for (List<String> sortedSeries: seriesPairs)
	        		series.getData().add(new XYChart.Data(sortedSeries.get(0), Long.parseLong(sortedSeries.get(1))));
	        	
	        	barChart.getData().add(series);
	        	
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
		
		
		
		public List<Integer> createYearList() {
			// System.out.println("hey");
			ArrayList<Integer> yearList = new ArrayList<Integer>(Arrays.asList(0));
			yearList.remove(0);
			
			for (Map.Entry<List<String>, Long> entry : valueFromIndicatorYearCountryMap.entrySet()) {
				List<String> keyList = entry.getKey();
				int year = Integer.parseInt(keyList.get(2));
				yearList.add(year);				
			}
			
			Collections.sort(yearList);
			
			
			return yearList;
			
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
