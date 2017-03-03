package main;

import java.util.ArrayList;
import java.util.Collection;

import front_end.customJavaFxNodes.ActionButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerMaster {

	private TabPane tabPane;
	private ActionButton newTabCreator;
	
	public ControllerMaster(){
		tabPane = new TabPane();
		newTabCreator = createNewTabCreator();
	}

	private ActionButton createNewTabCreator() {
		ActionButton newTabButton = new ActionButton("Create New Tab", e -> createNewGame());
		newTabButton.setLayoutX(700);
		return newTabButton;
	}

	private void createNewGame() {
		Tab newTab = new Tab("New Tab");
		Controller newRuntime = new Controller();
		newRuntime.start(newTab);
		tabPane.getTabs().add(newTab);
		tabPane.getSelectionModel().select(newTab);
	}

	public void start(Stage primaryStage) {
		Group myRoot = new Group();
		Scene scene = new Scene(myRoot, 900, 800, Color.GRAY);
		
		Controller firstRuntime = new Controller();
		
		Tab firstTab = new Tab("First Tab");
		tabPane.getTabs().add(firstTab);
		myRoot.getChildren().addAll(tabPane,newTabCreator);	
		
		
		firstRuntime.start(firstTab);
		
		primaryStage.setTitle("SLogo Team 16");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
