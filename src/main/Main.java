package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * Start the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ControllerMaster cM = new ControllerMaster();
		cM.start(primaryStage);
	}

}