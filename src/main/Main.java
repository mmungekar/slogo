package main;

import front_end.Animation;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	/**
	 * Start the program.
	 */
	public static void main (String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) 
	{
		Animation animation = new Animation();
		animation.start(primaryStage);
	}

}