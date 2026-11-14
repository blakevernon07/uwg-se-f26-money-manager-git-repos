package edu.westga.cs3211.moneymanager.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Defines the application.
 * 
 * @author Blake Vernon
 * @version 11/11/26
 */
public class Driver extends Application {

	/**
	 * Starts the application.
	 * 
	 * @param primaryStage The stage of the application.
	 */
	public void start(Stage primaryStage) throws IOException {
		Parent myParent = FXMLLoader.load(getClass().getResource("DriverView.fxml"));
		
		Scene myScene = new Scene(myParent);
		
		primaryStage.setTitle("Money Manager");
		primaryStage.setScene(myScene);
		primaryStage.show();
	}
	
	/**
	 * Opens the application.
	 * 
	 * @param args Arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
