package main.java.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class PreprocessLinechartComponents extends TemplatePreprocessChartComponents {
	private Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	
	public PreprocessLinechartComponents(Map<List<String>, Long> valueFromIndicatorYearCountryMap) {
		super(valueFromIndicatorYearCountryMap);
		this.valueFromIndicatorYearCountryMap = valueFromIndicatorYearCountryMap;
	}
	
		public void addSeriesToLinechartLegend(LineChart lineChart) {
			List<List<String>> country_indicatorPairList = getCountry_IndicatorPairs(filterListFromYears());
	        
	        
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
	        	lineChart.getData().add(series);
	        }		
		}


	
		
		
		
		
}
