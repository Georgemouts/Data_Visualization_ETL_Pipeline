package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UI extends Application {
	Stage window; 
	Scene scene ; 
	Button button;
	ListView<String> listView;
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws Exception{
    	window = primaryStage;
    	window.setTitle("the button");
    	button = new Button("Submit");
    	
    	listView = new ListView<>();
    	listView.getItems().addAll("Ena","dyo","tria");
    	listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	button.setOnAction(e->buttonClicked());
    	
    	
    	
    	VBox layout = new VBox(10);
    	layout.setPadding(new Insets(20,20,20,20));
    	layout.getChildren().addAll(listView,button);
    	
    	scene = new Scene(layout,300,250);
    	window.setScene(scene);
    	window.show();
    	
    }
    
    private void buttonClicked() {
    	String message ="";
    	ObservableList<String> movies;
    	movies =listView.getSelectionModel().getSelectedItems();
    	for(String m: movies) {
    		message +=m +"\n";
    	}
    	System.out.println(message);
    	
    }
   
}