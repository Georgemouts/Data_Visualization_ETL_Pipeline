package main.java.controller.charts;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import main.java.model.PreprocessLinechartComponents;


 
public class PlotTimelineChartController extends PlotChartController {
	
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
	
	@Override
	public void plotChart() {
		this.stage = new Stage();
		stage.setTitle("Timeline");
		
		
		long maxIndicatorValue = Collections.max(valueFromIndicatorYearCountryMap.values());
		long spacing = maxIndicatorValue / 100;
		

        //defining the axes
        xAxis = new NumberAxis(startingYear, endingYear, yearFormat);
        yAxis = new NumberAxis(Collections.min(valueFromIndicatorYearCountryMap.values()) - 4 * spacing, 
        		Collections.max(valueFromIndicatorYearCountryMap.values()) + 4 * spacing, spacing);
        
        xAxis.setLabel("Year");
        yAxis.setLabel("Value");
        
        //creating the chart
        lineChart =  new LineChart<Number,Number>(xAxis,yAxis);
        
        // lineChart.setTitle();
        PreprocessLinechartComponents preprocessing = new PreprocessLinechartComponents(valueFromIndicatorYearCountryMap); 
        preprocessing.addSeriesToLinechartLegend(lineChart);
        
        Scene scene  = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
	}
}
