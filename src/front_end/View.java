package front_end;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import back_end.ModelState;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class View implements ViewInterface {
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 1000;
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
	
	private ComboBox<Integer> turtleIDs;

	
	public static final ObservableList<String> customVariables = FXCollections.observableArrayList();
	public static final ObservableList<String> customCommands = FXCollections.observableArrayList();
	

	public View(Stage s) {
		setLanguage(DEFAULT_LANGUAGE);
		
		Group root = new Group();
		createViewComponents(s, root);
		
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, BACKGROUND_COLOR);

		s.setScene(scene);
		s.show();
	}
	
	public void update(ModelState state){
		canvas.update(state);
	}

	private void createViewComponents(Stage s, Group root) {
		createCanvas(root);
		createTerminal(root);
		createCustomViews(root);
		createSideButtons(s, root);
	}
	
	private void createSideButtons(Stage s, Group root) {
		VBox sideButtons = new VBox();
		
		sideButtons.getChildren().addAll(createLanguageDropDown(),
				createBackgroundColorSelectionTool(),
				addTurtleButton(),
				createTurtleImageSelection(s));
		
		sideButtons.setLayoutX(CANVAS_WIDTH + 3 * DEFAULT_SPACING);
		sideButtons.setLayoutY(DEFAULT_SPACING);
		sideButtons.setSpacing(DEFAULT_SPACING / 2);
		
		root.getChildren().add(sideButtons);
	}

	private ComboBox<String> createBackgroundColorSelectionTool() {
		// from
		// http://docs.oracle.com/javafx/2/ui_controls/list-view.htm
		
		ObservableList<String> data = FXCollections.observableArrayList(
	            "white","chocolate", "salmon", "gold", "coral", "darkorchid",
	            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
	            "blueviolet", "brown");
		
		ComboBox<String> backgroundColors = new ComboBox<String>();
		
		backgroundColors.setItems(data);
		backgroundColors.setPromptText("Select Background Color");
		backgroundColors.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
					@Override 
		            public ListCell<String> call(ListView<String> list) {
		                return new ColorRectCell();
		            }
	            }
	        );
		
		backgroundColors.getSelectionModel().selectedItemProperty().addListener(
	            new ChangeListener<String>() {
	                public void changed(ObservableValue<? extends String> ov, 
	                    String old_val, String new_val) {
	                	changeBackgroundColor(Color.web(new_val));
	            }
	        });
				
		return backgroundColors;
	}

	private void createCustomViews(Group root) {
		TabPane tabPane = new TabPane();
	    
	    Tab tabCommands = new Tab();
	    tabCommands.setText("Custom Commands");
	    tabCommands.setContent(createCommandView());
	    
	    Tab tabVariables = new Tab();
	    tabVariables.setText("Custom Variables");
	    tabVariables.setContent(createVariableView());
	    
	    tabPane.getTabs().addAll(tabCommands, tabVariables);
	    tabPane.setLayoutY(WINDOW_HEIGHT - 350);
		tabPane.setLayoutX(CANVAS_WIDTH + 3 * DEFAULT_SPACING);
		tabPane.setPrefHeight(350 - DEFAULT_SPACING);
		
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
	    
	    root.getChildren().add(tabPane);
	}

	private void createCanvas(Group root) {
		canvas = new Canvas(root, HOME);
		canvas.setPosition(new int[] { DEFAULT_SPACING, DEFAULT_SPACING });
		canvas.setSize(new int[] { CANVAS_WIDTH, CANVAS_HEIGHT });
		canvas.setBackgroundColor(canvasColor);
	}
	
	private void createTerminal(Group root) {
		terminal = new Terminal();
		VBox console = terminal.getConsole();
		console.setSpacing(DEFAULT_SPACING);
		console.setLayoutY(WINDOW_HEIGHT - 350);
		console.setLayoutX(40);
		root.getChildren().add(console);
	}

	private Button addTurtleButton() {
		Button turtleCreation = new Button("Add New Turtle");
		turtleCreation.setOnAction(event -> {createTurtle(); updateTurtleSelection();});

		return turtleCreation;
	}

	private void updateTurtleSelection() {
		turtleIDs.getItems().clear();
		turtleIDs.getItems().addAll(canvas.getTurtleIDs());
	}

	private HBox createTurtleImageSelection(Stage s) {
		HBox turtleImageControl = new HBox();
		
		turtleIDs = new ComboBox<Integer>(); 
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
		
		turtleImageControl.setLayoutX(CANVAS_WIDTH + 3 * DEFAULT_SPACING);
		turtleImageControl.setLayoutY(3 * DEFAULT_SPACING);
		return turtleImageControl;
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

	/*
	 * I know this is repeated code.
	 * The createVariableView method will be changed once I 
	 * figure out how to make it editable.
	 * 
	 * TODO: Make values in this editable, not executable
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
	
	public void createTurtle(){
		canvas.createTurtle();
	}
	
	public void changeBackgroundColor(Color newColor){
		canvas.setBackgroundColor(newColor);
	}
	
	public void setOutputText(String output){
		terminal.setOutputText(output);
	}
	
	
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
	
	
	private static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }
}
