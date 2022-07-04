package main.java.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("..//resources//main//SelectionsWindow.fxml"));
		scene = new Scene(root, 500, 400);
		
		primaryStage.setTitle("Selections");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
