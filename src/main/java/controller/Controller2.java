package main.java.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.dao.Connector;

public class Controller2 implements Initializable {
	Connector connection = new Connector();
	
	
    @FXML
    private Label Label1;

    @FXML
    private Label Label2;

    
    public void makeQuery(List<String> ... listOfLists) {
    	connection.connectWithDB();
    	// connection.
    	List<List<String>> listsForQuery = new ArrayList<List<String>>();
    	
    	for(List<String> lst: listOfLists)
    		listsForQuery.add(lst);
 
    	
    	connection.test(listsForQuery);
    }
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
    
}
