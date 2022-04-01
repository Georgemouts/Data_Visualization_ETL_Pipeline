package main.java.controller.charts;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.controller.Controller;


 
public class PlotTimelineChartController {
	
	private static Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	private static int yearFormat;
	private static int startingYear;
	private static int endingYear;
	private static NumberAxis xAxis;
	private static NumberAxis yAxis;
	private static Stage stage;
	private static LineChart<Number, Number> lineChart;
	
	
	public PlotTimelineChartController() {
	}
	
	public PlotTimelineChartController(Map<List<String>, Long>valueFromIndicatorYearCountryMap, 
			int yearFormat, int startingYear, int endingYear) {
		super();
		this.valueFromIndicatorYearCountryMap = valueFromIndicatorYearCountryMap;
		this.yearFormat = yearFormat;
		this.startingYear = startingYear;
		this.endingYear = endingYear;
	}
	

	
	
	/**
	 * Every country-indicator pair is an individual XYChart.Series object.
	 * 
	 */
	
	public void plotChart() {
		this.stage = new Stage();
		stage.setTitle("Linechart");
		
		
		long maxIndicatorValue = Collections.max(valueFromIndicatorYearCountryMap.values());
		long spacing = Collections.max(valueFromIndicatorYearCountryMap.values()) / 100;
		

        //defining the axes
        xAxis = new NumberAxis(startingYear, endingYear, yearFormat);
        yAxis = new NumberAxis(Collections.min(valueFromIndicatorYearCountryMap.values()), 
        		Collections.max(valueFromIndicatorYearCountryMap.values()) + 2 * spacing, spacing);
        
        xAxis.setLabel("Year");
        yAxis.setLabel("Value");
        
        //creating the chart
        lineChart =  new LineChart<Number,Number>(xAxis,yAxis);
        
        // lineChart.setTitle();
        addSeriesToLinechartLegend(lineChart);
        
        Scene scene  = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
	}
	
	
	// TODO: move it to main.java.model 
	public void addSeriesToLinechartLegend(LineChart lineChart) {
		List<List<String>> country_indicatorPairList = getCountry_IndicatorPairs(filterListFromYears());
        System.gc();
        
        List<String> country_indicatorSeries = new ArrayList<String>();
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
	
	
	
	// TODO: move it to main.java.model
	
	/**
	 * 
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
