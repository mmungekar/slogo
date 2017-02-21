package front_end;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		createCanvas(root);
		createBottomBar(root);
		createSideBar(root);
		// TODO add turtle
		setLanguage(DEFAULT_LANGUAGE);

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, BACKGROUND_COLOR);

		s.setScene(scene);
		s.show();
	}

	private void createSideBar(Group root) {
		VBox sideBar = new VBox();
		
		sideBar.getChildren().add(createCustomsView(CUSTOM_COMMANDS));
		sideBar.getChildren().add(createCustomsView(CUSTOM_VARIABLES));

		sideBar.setSpacing(DEFAULT_SPACING);
		sideBar.setLayoutY(DEFAULT_SPACING);
		sideBar.setLayoutX(CANVAS_WIDTH + 100);
		
		root.getChildren().add(sideBar);

	}

	private ListView<String> createCustomsView(String customType) {
		ListView<String> myListView = new ListView<String>();
		if (customType.equals(CUSTOM_COMMANDS)){
			myListView.setItems(customCommands);
		} else if (customType.equals(CUSTOM_VARIABLES)) {
			myListView.setItems(customVariables);
		}
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
						// adds the selected string to the input
						terminal.setText(terminal.getText() + cell.getItem());
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

	private void createCanvas(Group root) {
		canvas = new Canvas(root);
		canvas.setPosition(new int[] { DEFAULT_SPACING, DEFAULT_SPACING });
		canvas.setSize(new int[] { CANVAS_WIDTH, CANVAS_HEIGHT });
		canvas.setBackgroundColor(canvasColor);
	}

	public void update(Object changes) {
		if (changes == null)
			return;

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
