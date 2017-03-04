package main;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerMaster {

	public static final String TAB_SIZE = "                  ";

	private TabPane tabPane;
	
	public ControllerMaster()
	{
		tabPane = new TabPane();
	}

	private void createNewGame() {
		Tab newTab = new Tab(TAB_SIZE);
		Controller newRuntime = new Controller();
		tabPane.getTabs().add(newTab);
		newRuntime.start(newTab);
		newRuntime.setNewTabButton(() -> createNewGame());
		tabPane.getSelectionModel().select(newTab);
	}

	public void start(Stage primaryStage)
	{
		BorderPane myRoot = new BorderPane();
		Scene scene = new Scene(myRoot, 900, 800, Color.BLUE);
		myRoot.setTop(tabPane);
		
		createNewGame();
		
		primaryStage.setTitle("SLogo Team 16");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
