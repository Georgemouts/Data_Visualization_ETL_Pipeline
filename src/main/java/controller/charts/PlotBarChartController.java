package main.java.controller.charts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import main.java.model.PreprocessBarchartComponents;


public class PlotBarChartController extends PlotChartController {
	private static Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	private static int yearFormat;
	private static int startingYear;
	private static int endingYear;
	private static CategoryAxis xAxis;
	private static NumberAxis yAxis;
	private static Stage stage;
	private static BarChart<String, Number> barChart;
	
	
	public PlotBarChartController() {
	}
	
	public PlotBarChartController(Map<List<String>, Long>valueFromIndicatorYearCountryMap, 
			int yearFormat, int startingYear, int endingYear) {
		super();
		this.valueFromIndicatorYearCountryMap = valueFromIndicatorYearCountryMap;
		this.yearFormat = yearFormat;
		this.startingYear = startingYear;
		this.endingYear = endingYear;
	}
	
    @Override
     public void plotChart() {
    	this.stage = new Stage();
		stage.setTitle("Bar Chart");
		
		
		
		

        //defining the axes
        xAxis = new CategoryAxis();
        
      
        
  
    	
    	long maxIndicatorValue = Collections.max(valueFromIndicatorYearCountryMap.values());
		long spacing = maxIndicatorValue / 100;
    	
        yAxis = new NumberAxis(Collections.min(valueFromIndicatorYearCountryMap.values()), 
        		Collections.max(valueFromIndicatorYearCountryMap.values()) + 2 * spacing, spacing);
        
        xAxis.setLabel("Year");
        yAxis.setLabel("Value");
        
        //creating the chart
        barChart =  new BarChart<String, Number>(xAxis, yAxis);
        
        // lineChart.setTitle();
        PreprocessBarchartComponents preprocessing = new PreprocessBarchartComponents(valueFromIndicatorYearCountryMap); 
        preprocessing.addSeriesToBarchartLegend(startingYear, endingYear, barChart);
    
        Scene scene  = new Scene(barChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }


	
}
