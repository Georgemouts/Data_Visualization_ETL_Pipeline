package main.java.controller.charts;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.stage.Stage;
import main.java.model.PreprocessLinechartComponents;
import main.java.model.PreprocessScatterchartComponents;
import main.java.model.TemplatePreprocessChartComponents;


 
public class PlotScatterChartController extends PlotChartController {
	
	private static Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	private static int yearFormat;
	private static int startingYear;
	private static int endingYear;
	private static NumberAxis xAxis;
	private static NumberAxis yAxis;
	private static Stage stage;
	private static ScatterChart<Number, Number> scatterChart;
	
	
	public PlotScatterChartController() {
	}
	
	public PlotScatterChartController(Map<List<String>, Long>valueFromIndicatorYearCountryMap, 
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
		stage.setTitle("Scatter Plot");
		
		
		long maxIndicatorValue = Collections.max(valueFromIndicatorYearCountryMap.values());
		long spacing = maxIndicatorValue / 100;
		

        //defining the axes
        xAxis = new NumberAxis(startingYear, endingYear, yearFormat);
        yAxis = new NumberAxis(Collections.min(valueFromIndicatorYearCountryMap.values()) - 4 * spacing, 
        		Collections.max(valueFromIndicatorYearCountryMap.values()) + 4 * spacing, spacing);
        
        xAxis.setLabel("Year");
        yAxis.setLabel("Value");
        
        //creating the chart
        scatterChart =  new ScatterChart<Number,Number>(xAxis,yAxis);
        
  
        PreprocessScatterchartComponents preprocessing = new PreprocessScatterchartComponents(valueFromIndicatorYearCountryMap); 
        preprocessing.addSeriesToScatterchartLegend(scatterChart);
        
        Scene scene  = new Scene(scatterChart, 800, 600);
        stage.setScene(scene);
        stage.show();
	}

	public static Map<List<String>, Long> getValueFromIndicatorYearCountryMap() {
		return valueFromIndicatorYearCountryMap;
	}

	public static int getYearFormat() {
		return yearFormat;
	}

	public static int getStartingYear() {
		return startingYear;
	}

	public static int getEndingYear() {
		return endingYear;
	}

	public static void setValueFromIndicatorYearCountryMap(Map<List<String>, Long> valueFromIndicatorYearCountryMap) {
		PlotScatterChartController.valueFromIndicatorYearCountryMap = valueFromIndicatorYearCountryMap;
	}

	public static void setYearFormat(int yearFormat) {
		PlotScatterChartController.yearFormat = yearFormat;
	}

	public static void setStartingYear(int startingYear) {
		PlotScatterChartController.startingYear = startingYear;
	}

	public static void setEndingYear(int endingYear) {
		PlotScatterChartController.endingYear = endingYear;
	}
	
	
	
	
	
	
	
	
	
	
}
