package main.java.controller.charts;

import java.util.List;
import java.util.Map;

public abstract class PlotChartController {
	private static Map<List<String>, Long> valueFromIndicatorYearCountryMap;
	
	abstract public void plotChart();
}
