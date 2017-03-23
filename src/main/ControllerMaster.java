package main;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Juan Philippe, Miguel Anderson
 *
 */
public class ControllerMaster {

	private static final String APP_TITLE = "SLogo Team 16";
	private static final int APP_WINDOW_WIDTH = 800;
	private static final int APP_WINDOW_HEIGHT = 900;
	private static final Color TAB_BACKGROUND_COLOR = Color.AQUA;
	public static final String TAB_TITLE_FORMAT = "Tab %d";

	private TabPane tabPane;
	private Integer tabNumber;
	
	public ControllerMaster()
	{
		tabPane = new TabPane();
		tabNumber = 0;
	}

	private void createNewGame() {
		tabNumber ++;
		Tab newTab = new Tab(String.format(TAB_TITLE_FORMAT, tabNumber));
		Controller newRuntime = new Controller();
		tabPane.getTabs().add(newTab);
		newRuntime.start(newTab);
		newRuntime.setNewTabButton(() -> createNewGame());
		tabPane.getSelectionModel().select(newTab);
	}

	/**
	 * Sets up scene and the BorderPane that is used to organize the GUI
	 * @param primaryStage
	 */
	public void start(Stage primaryStage)
	{
		BorderPane myRoot = new BorderPane();
		Scene scene = new Scene(myRoot, APP_WINDOW_HEIGHT, APP_WINDOW_WIDTH);
		myRoot.setTop(tabPane);
		myRoot.setBackground(new Background(new BackgroundFill(TAB_BACKGROUND_COLOR, null, null)));
		
		createNewGame();
		
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
