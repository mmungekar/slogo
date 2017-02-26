package front_end;

import java.util.ResourceBundle;
import java.util.function.Consumer;


import back_end.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View implements ViewInterface {
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 1000;
	public static final int CANVAS_HEIGHT = 400;
	public static final int CANVAS_WIDTH = 600;
	public static final Color BACKGROUND_COLOR = Color.GREY;
	public static final String DEFAULT_LANGUAGE = "English";
	public static final int DEFAULT_SPACING = 30;
	public static final Point2D HOME = new Point2D(CANVAS_WIDTH / 2 + DEFAULT_SPACING,
			CANVAS_HEIGHT / 2 + DEFAULT_SPACING);
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";
	private ResourceBundle resource;

	private Consumer<String> onMessageReceivedHandler;
	private Consumer<String> languageHandler;

	
	private Terminal terminal;
	private Canvas canvas;
	private Color canvasColor = Color.WHITE;
	private String currentLanguage;


	public static final ObservableList<String> customVariables = FXCollections.observableArrayList();
	public static final ObservableList<String> customCommands = FXCollections.observableArrayList();


	public View(Stage s, Model model) {
		setLanguage(DEFAULT_LANGUAGE);

		Group root = new Group();
		createViewComponents(s, model, root);

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, BACKGROUND_COLOR);

		s.setScene(scene);
		s.show();
	}

	private void createViewComponents(Stage s, Model model, Group root) {
		createCanvas(model, root);
		createTerminal(root);
		createCustomViews(root);
		SideBar sideBar = new SideBar(s, model, root, this);
	}

	private void createCustomViews(Group root) {
		TabPane tabPane = new TabPane();

		Tab tabCommands = new Tab();
		tabCommands.setText(resource.getString("CustomCommands"));
		tabCommands.setContent(createCommandView());

		Tab tabVariables = new Tab();
		tabVariables.setText(resource.getString("CustomVariables"));
		tabVariables.setContent(createVariableView());

		tabPane.getTabs().addAll(tabCommands, tabVariables);
		tabPane.setLayoutY(WINDOW_HEIGHT - 350);
		tabPane.setLayoutX(CANVAS_WIDTH + 3 * DEFAULT_SPACING);
		tabPane.setPrefHeight(350 - DEFAULT_SPACING);

		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		root.getChildren().add(tabPane);
	}

	private void createCanvas(Model model, Group root) {
		canvas = new Canvas(model, root, HOME);
		canvas.setPosition(new int[] { DEFAULT_SPACING, DEFAULT_SPACING });
		canvas.setSize(new int[] { CANVAS_WIDTH, CANVAS_HEIGHT });
		canvas.setBackgroundColor(canvasColor);
	}

	private void createTerminal(Group root) {
		terminal = new Terminal(this.currentLanguage);
		VBox console = terminal.getConsole();
		console.setSpacing(DEFAULT_SPACING);
		console.setLayoutY(WINDOW_HEIGHT - 350);
		console.setLayoutX(40);
		root.getChildren().add(console);
	}


	/*
	 * I know this is repeated code. The createVariableView method will be
	 * changed once I figure out how to make it editable.
	 * 
	 * TODO: Make values in this editable, not executable
	 */
	private ListView<String> createVariableView() {
		/*
		 * TableView table = new TableView(); TableColumn variableNameCol = new
		 * TableColumn("Name"); TableColumn variableValueCol = new
		 * TableColumn("Value"); variableNameCol.setEditable(false);
		 * variableValueCol.setEditable(true);
		 */

		ListView<String> myListView = new ListView<String>();
		myListView.setItems(customVariables);
		myListView.setPrefWidth(150);
		myListView.setPrefHeight(175);
		setClickActionOnCustomListView(myListView); // this will be replaced
		return myListView;
	}

	private ListView<String> createCommandView() {
		ListView<String> myListView = new ListView<String>();
		myListView.setItems(customCommands);
		myListView.setPrefWidth(150);
		myListView.setPrefHeight(175);
		setClickActionOnCustomListView(myListView);
		return myListView;
	}

	private void setClickActionOnCustomListView(ListView<String> lW) {
		// http://stackoverflow.com/questions/23622703/deselect-an-item-on-an-javafx-listview-on-click
		lW.setCellFactory(lv -> {
			ListCell<String> cell = new ListCell<>();
			cell.textProperty().bind(cell.itemProperty());
			cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
				lW.requestFocus();
				if (!cell.isEmpty()) {
					int index = cell.getIndex();
					if (!lW.getSelectionModel().getSelectedIndices().contains(index)) {
						lW.getSelectionModel().select(index);
						// add commmand to terminal and execute
						terminal.setText(cell.getItem());
						terminal.submitInput();
						lW.getSelectionModel().clearSelection(index);
					}
					event.consume();
				}
			});
			return cell;
		});
	}

	public void setEnterListener(Consumer<String> action) {
		this.onMessageReceivedHandler = action;
		terminal.setEnterListener(action);

	}

	public String getLanguage() {
		return this.currentLanguage;
	}

	void setLanguage(String language) {
		this.currentLanguage = language;
		if (this.languageHandler != null) {
			this.languageHandler.accept(language);
		}
		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + language);
		// TODO how to reset everything with new language
	}

	public void setLanguageChangeListener(final Consumer<String> action) {
		this.languageHandler = action;

	}

	public void changeBackgroundColor(Color newColor) {
		canvas.setBackgroundColor(newColor);
	}

	public void setOutputText(String output) {
		terminal.setOutputText(output);
	}
	
	double[] getCanvasDimensions(){
		return new double[]{CANVAS_WIDTH, CANVAS_HEIGHT};
	}
	
	double getDefaultSpacing(){
		return DEFAULT_SPACING;
	}
	
	ResourceBundle getCurrentResource(){
		return resource;
	}

	public void printToOutput(Exception e) {
		terminal.printToOutput(e);
		
	}


}
