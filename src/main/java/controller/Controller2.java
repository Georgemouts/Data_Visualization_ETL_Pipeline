package main.java.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.dao.Connector;
import main.java.dao.CountryDAOMySQLImpl;
import main.java.dao.IndicatorDAOMySQLImpl;
import main.java.dao.ValueFromCountryAndIndicatorDAOMySQLImpl;


public class Controller2 implements Initializable {
	Connector connection = new Connector();
	
	
    @FXML
    private Label Label1;

    @FXML
    private Label Label2;

    
    public void test_ind(List<String> cname, List<String> iname, List<Integer> year) {
    	CountryDAOMySQLImpl implc = new CountryDAOMySQLImpl();
    	List<Integer> cid = implc.readCountryIdFromName(cname);
    	
    	IndicatorDAOMySQLImpl impli = new IndicatorDAOMySQLImpl();
    	List<Integer> iid = impli.readIndicatorIdFromName(iname);
    	ValueFromCountryAndIndicatorDAOMySQLImpl impl = new ValueFromCountryAndIndicatorDAOMySQLImpl();
    	impl.readValueFromCountryAndIndicator(cid, iid, year);
    	
    	
    }
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
    
}
