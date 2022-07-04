package main.java.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



public abstract class TemplatePreprocessChartComponents {
	private Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	
	public TemplatePreprocessChartComponents(Map<List<String>, Long> valueFromIndicatorYearCountryMap) {
		this.valueFromIndicatorYearCountryMap = valueFromIndicatorYearCountryMap;
	}
	
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
	

	
	
	
}
