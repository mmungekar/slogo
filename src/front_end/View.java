package front_end;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import back_end.ModelState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class View implements ViewInterface {
	public static final int WINDOW_HEIGHT = 700;
	public static final int WINDOW_WIDTH = 900;
	public static final int CANVAS_HEIGHT = 400;
	public static final int CANVAS_WIDTH = 600;
	public static final Color BACKGROUND_COLOR = Color.GREY;
	public static final String DEFAULT_LANGUAGE = "English";
	public static final String CUSTOM_COMMANDS = "customCommands";
	public static final String CUSTOM_VARIABLES = "customVariables";
	public static final int DEFAULT_SPACING = 30;
	public static final Point2D HOME = new Point2D(CANVAS_WIDTH / 2 + DEFAULT_SPACING, CANVAS_HEIGHT / 2 + DEFAULT_SPACING);
	public static final String IMAGE_FILE_DIRECTORY = "src/resources/images/";
	public static final String IMAGE_EXTENSION = ".gif";

	private Consumer<String> onMessageReceivedHandler;
	private Consumer<String> languageHandler;

	private List<String> languages = Arrays.asList(new String[] { "Chinese", "English", "French", "German", "Italian",
			"Portuguese", "Russian", "Spanish", "Syntax" });

	private Terminal terminal;
	private Canvas canvas;
	private Color canvasColor = Color.WHITE;
	private String currentLanguage;

	
	public static final ObservableList<String> customVariables = FXCollections.observableArrayList();
	public static final ObservableList<String> customCommands = FXCollections.observableArrayList();
	

	public View(Stage s) {
		Group root = new Group();
		createViewComponents(s, root);
		setLanguage(DEFAULT_LANGUAGE);
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, BACKGROUND_COLOR);

		s.setScene(scene);
		s.show();
	}

	private void createViewComponents(Stage s, Group root) {
		createCanvas(root);
		createBottomBar(root);
		createSideBar(root);
		createTurtleImageSelection(s, root);
	}

	private void createTurtleImageSelection(Stage s, Group root) {
		VBox turtleImageControl = new VBox();
		
		ComboBox<Integer> turtleIDs = new ComboBox<Integer>(); 
		turtleIDs.setPromptText("Turtle IDs");
		turtleIDs.getItems().addAll(canvas.getTurtleIDs());
		
		Button fileChoose = new Button("Choose Turtle Image");
		fileChoose.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null){
					File newImageFile = chooseFile(s);
					if(newImageFile != null){
						canvas.changeTurtleImage(turtleIDs.getSelectionModel().getSelectedItem(), newImageFile);
					} else {
						// wrong file
					}
				} else {
					// no turtle ID selected
				}
			}
		});
		
		turtleImageControl.getChildren().addAll(turtleIDs, fileChoose);
		
		turtleImageControl.setLayoutX(WINDOW_WIDTH - 5 * DEFAULT_SPACING);
		turtleImageControl.setLayoutY(WINDOW_HEIGHT - 4 * DEFAULT_SPACING);
		root.getChildren().add(turtleImageControl);
	}
	
	private File chooseFile(Stage s){
		FileChooser xmlChooser = new FileChooser();
		xmlChooser.setTitle("Choose Turtle Image");
		xmlChooser.setInitialDirectory(new File(IMAGE_FILE_DIRECTORY));
		File file = xmlChooser.showOpenDialog(s);
		if(file != null){
				String name = file.getName();
				String fileType = name.substring(name.lastIndexOf("."), name.length());
				if(!fileType.equals(IMAGE_EXTENSION)){
					return null;
				}
				return file;
		}
		return null;
	}

	private void createSideBar(Group root) {
		VBox sideBar = new VBox();
		
		sideBar.getChildren().addAll(createCommandView(), createVariableView());

		sideBar.setSpacing(DEFAULT_SPACING);
		sideBar.setLayoutY(DEFAULT_SPACING);
		sideBar.setLayoutX(CANVAS_WIDTH + 100);
		
		root.getChildren().add(sideBar);

	}

	/*
	 * I know this is repeated code.
	 * The createVariableView method will be changed once I 
	 * figure out how to make it editable.
	 */
	private ListView<String> createVariableView() {
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

	private void createBottomBar(Group root) {

		terminal = new Terminal();

		VBox console = terminal.getConsole();
		console.setSpacing(DEFAULT_SPACING);

		/*

		 * ComboBox<String> languageDropDown = createLanguageDropDown();
		 * 
		 * buttonPanel.getChildren().addAll(submit, languageDropDown);
		 * buttonPanel.setSpacing(30);
		 * 
		 * HBox bottomBar = new HBox(console, buttonPanel);
		 */
		

		console.setLayoutY(WINDOW_HEIGHT - 250);
		console.setLayoutX(40);
		
		root.getChildren().add(console);

	}

	/*
	private ComboBox<String> createLanguageDropDown() {
		// from stack overflow:
		// http://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
		ObservableList<String> obsNames = FXCollections.observableArrayList(languages);
		ComboBox<String> languageDropDown = new ComboBox<String>(obsNames);
		languageDropDown.setMaxWidth(200);
		languageDropDown.setPromptText("Set Language");
		languageDropDown.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String oldParam, String newParam) {
				setLanguage(newParam);
			}
		});
		return languageDropDown;
	}
	*/

	private void createCanvas(Group root) {
		canvas = new Canvas(root, HOME);
		canvas.setPosition(new int[] { DEFAULT_SPACING, DEFAULT_SPACING });
		canvas.setSize(new int[] { CANVAS_WIDTH, CANVAS_HEIGHT });
		canvas.setBackgroundColor(canvasColor);
	}

	public void update(ModelState state)
	{
		canvas.update(state);
	}

	public void setEnterListener(Consumer<String> action) {
		this.onMessageReceivedHandler = action;
		terminal.setEnterListener(action);

	}

	public String getLanguage() {
		return this.currentLanguage;
	}

	protected void setLanguage(String language) {
		this.currentLanguage = language;
		if (this.languageHandler != null) {
			this.languageHandler.accept(language);
		}
	}

	public void setLanguageChangeListener(final Consumer<String> action) {
		this.languageHandler = action;

	}
}
