package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.java.controller.charts.PlotChartController;
import main.java.controller.charts.PlotChartControllerFactory;
import main.java.dao.CountryDAO;
import main.java.dao.CountryDAOFactory;
import main.java.dao.IndicatorDAO;
import main.java.dao.IndicatorDAOFactory;
import main.java.dao.Names;
import main.java.dao.ValueFromCountryAndIndicatorDAO;
import main.java.dao.ValueFromCountryAndIndicatorDAOFactory;

public class Controller implements Initializable {
	
	List<String> selected_countries = new ArrayList<String>();
	List<String> selected_indicators = new ArrayList<String>();
	List<Integer> years = new ArrayList<Integer>();
	String startingyear, endingyear, yearformat, typeOfChart;
	
	
	
	@FXML
	private ComboBox countryComb;
	@FXML
	private ComboBox indicatorComb;
	@FXML
	private ComboBox startingyearComb;
	@FXML
	private ComboBox endingyearComb;
	@FXML
	private ComboBox yearformatComb;
	@FXML
    private ComboBox charttypeComb;
	
    @FXML
    private Button submitButton;
   

	@FXML
	void Select1(ActionEvent event) {
		String s = countryComb.getSelectionModel().getSelectedItem().toString();
		selected_countries.add(s);
	}
	
	@FXML
	void Select2(ActionEvent event) {
		String s = indicatorComb.getSelectionModel().getSelectedItem().toString();
		selected_indicators.add(s);
	}
	
	@FXML
	void Select3(ActionEvent event) {
		yearformat = yearformatComb.getSelectionModel().getSelectedItem().toString();
	}

	@FXML
	void Select4(ActionEvent event) {
		startingyear = startingyearComb.getSelectionModel().getSelectedItem().toString();
	}

	@FXML
	void Select5(ActionEvent event) {
		endingyear = endingyearComb.getSelectionModel().getSelectedItem().toString();
	}
	
	@FXML
    void Select6(ActionEvent event) {
		typeOfChart = charttypeComb.getSelectionModel().getSelectedItem().toString();
    }
	
	
	
	@FXML
	void Submit(ActionEvent event) throws IOException {  
		// actions that succeed after pressing the submit button.
		closeWindow(event);
		
		
		
		// create a test for getResourcePath
		Parent root = FXMLLoader.load(getClass().getResource(getResourcePath(typeOfChart)));
	
		
		createYearsList();
		
		ValueFromCountryAndIndicatorDAO indicates_table_accessor_obj = ValueFromCountryAndIndicatorDAOFactory.getValueFromCountryAndIndicatorDAO("mysql");
		CountryDAO country_table_accessor_obj = CountryDAOFactory.getCountryDAO("mysql");
		IndicatorDAO indicator_table_accessor_obj = IndicatorDAOFactory.getIndicatorDAO("mysql");
		
		
		List<Integer> cids = country_table_accessor_obj.readCountryIdFromName(selected_countries);
		List<Integer> iids = indicator_table_accessor_obj.readIndicatorIdFromName(selected_indicators);
		
		Map<List<String>, Long> mapForChart;
		mapForChart = indicates_table_accessor_obj.readValueFromCountryAndIndicator(cids, iids, years);
		
		
		PlotChartController plot_chart_obj = PlotChartControllerFactory.getPlotChartController(typeOfChart, mapForChart, Integer.parseInt(this.yearformat), 
				Integer.parseInt(this.startingyear), Integer.parseInt(this.endingyear));
		plot_chart_obj.plotChart();
		
	}
	
	 String getResourcePath(String typeOfChart) {
		 String fxmlResourcePath = "";
		 
		 if (typeOfChart.equalsIgnoreCase("bar")) 
			 fxmlResourcePath =  "..//resources//barchart//BarChart.fxml";
		 
		 else if (typeOfChart.equalsIgnoreCase("timeline")) 
			 fxmlResourcePath =  "..//resources//timelinechart//TimelineChart.fxml";
		 
		 else if (typeOfChart.equalsIgnoreCase("scatter")) 
			 fxmlResourcePath =  "..//resources/scatterchart//ScatterChart.fxml";
		 
		 return fxmlResourcePath;
			  
	 }
	
	
	void closeWindow(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Names names_object = Names.getInstance();		
		
		ObservableList<String> countryListForCombo = FXCollections.observableArrayList(names_object.getCountries());
		countryComb.setItems(countryListForCombo);

		
		names_object.getNY_indic().addAll(names_object.getSE_indic());	// merge NY and SE indicator families' lists.
		
		
		ObservableList<String> indicatorListForCombo = FXCollections.observableArrayList(names_object.getNY_indic());
		indicatorComb.setItems(indicatorListForCombo);
		
		ObservableList<String> yearFormatListForCombo = FXCollections.observableArrayList(names_object.getYearSpan());
		yearformatComb.setItems(yearFormatListForCombo);
		
		ObservableList<String> yearsListForCombo = FXCollections.observableArrayList(names_object.getYears());
		startingyearComb.setItems(yearsListForCombo);
		endingyearComb.setItems(yearsListForCombo);
		
		ObservableList<String> chartTypeListForCombo = FXCollections.observableArrayList(names_object.getChartTypes());
		charttypeComb.setItems(chartTypeListForCombo);
	}
	
	
	
	public void createYearsList() {
		int yearformat = Integer.parseInt(this.yearformat);
		int startingyear = Integer.parseInt(this.startingyear);
		int endingyear = Integer.parseInt(this.endingyear);
		int i;
		
		isAcceptableForm(yearformat, startingyear, endingyear);
		
		for (i = startingyear; i < endingyear + 1; i ++)
			this.years.add(i);
	}
	
	public void isAcceptableForm(int yearformat, int startingyear, int endingyear) {
		if (endingyear - startingyear < 0) {
			System.out.println("Aborting.");
			System.out.println("Ending year must be greater than starting year.");
			System.exit(-1);
		}
	}
	
	
	public List<String> getSelected_indicators() {
		return selected_indicators;
	}

	public void setSelected_indicators(List<String> selected_indicators) {
		this.selected_indicators = selected_indicators;
	}


	
	
	
	

}
