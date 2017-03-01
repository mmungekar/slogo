package front_end;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import back_end.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SideBar extends VBox {
	private static final String RESOURCES_MISC_HELP_HTML = "/resources/misc/help.html";
	public static final String IMAGE_FILE_DIRECTORY = "src/resources/images/";
	public static final String IMAGE_EXTENSION = ".gif";
	private List<String> languages = Arrays.asList(
			new String[] { "Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish" });

	private View myView;
	private Model model;

	private ComboBox<Integer> turtleIDs;

	// all objects that have titles
	private Button helpButton;
	private ComboBox<String> languageDropDown;
	private ComboBox<String> backgroundColors;
	private Button clearLines;
	private Button turtleCreation;
	private ComboBox<String> penColors;
	private RadioButton penUp;
	private RadioButton penDown;
	private Button sendHome;
	private Button fileChoose;

	public SideBar(Stage s, Model model, Group root, View view) {
		this.myView = view;
		this.model = model;

		this.getChildren().addAll(createHelpButton(), createLanguageDropDown(), createBackgroundColorSelectionTool(),
				createClearLinesButton(root), addTurtleButton(), createTurtleSpecficCommands(s));

	}

	private Node createHelpButton() {
		helpButton = new Button();
		helpButton.setOnAction(event -> displayHelpScreen());
		return helpButton;
	}

	private void displayHelpScreen() {
		Group root = new Group();
		WebView browser = new WebView();
		Scene helpScene = new Scene(root);
		root.getChildren().add(browser);
		Stage helpStage = new Stage();
		helpStage.setTitle("Help Page");
		helpStage.setScene(helpScene);
		URL url = getClass().getResource(RESOURCES_MISC_HELP_HTML);
		browser.getEngine().load(url.toExternalForm());
		helpStage.show();
	}

	private ComboBox<String> createLanguageDropDown() {
		// from stack overflow:
		// http://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
		ObservableList<String> obsNames = FXCollections.observableArrayList(languages);
		languageDropDown = new ComboBox<String>(obsNames);
		languageDropDown.setMaxWidth(200);
		languageDropDown.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldParam, String newParam) {
				myView.setLanguage(newParam);
				model.setCurrentLanguage(newParam);
			}
		});
		return languageDropDown;
	}

	private ComboBox<String> createBackgroundColorSelectionTool() {
		// from
		// http://docs.oracle.com/javafx/2/ui_controls/list-view.htm

		ObservableList<String> data = FXCollections.observableArrayList("white", "chocolate", "salmon", "gold", "coral",
				"darkorchid", "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue", "blueviolet", "brown");

		backgroundColors = new ComboBox<String>();

		backgroundColors.setItems(data);
		backgroundColors.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectCell();
			}
		});

		backgroundColors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				myView.changeBackgroundColor(Color.web(new_val));
				model.setBackgroundColor(Color.web(new_val));
			}
		});

		return backgroundColors;
	}

	private Node createClearLinesButton(Group root) {
		clearLines = new Button();
		clearLines.setOnAction(event -> clearLines(root));
		return clearLines;
	}

	private void clearLines(Group root) {
		root.getChildren().removeIf(new LinePredicate());
	}

	private Button addTurtleButton() {
		turtleCreation = new Button();
		turtleCreation.setOnAction(event -> {
			createTurtle();
			updateTurtleSelection();
		});
		return turtleCreation;
	}

	private void createTurtle() {
		model.createTurtle();
	}

	private void updateTurtleSelection() {
		for (Integer ID : model.getTurtleContainer().keySet()) {
			if (!turtleIDs.getItems().contains(ID)) {
				turtleIDs.getItems().add(ID);
			}
		}
	}

	private Pane createTurtleSpecficCommands(Stage s) {
		HBox turtleSpecificControls = new HBox();

		VBox turtleOptions = new VBox();

		createTurtleIDSelector();

		Button imageChoose = createTurtleImageChoose(s);
		Button sendHome = createSendHomeButton();
		VBox penControls = createPenToggle();

		turtleOptions.getChildren().addAll(imageChoose, sendHome, penControls);
		turtleOptions.setSpacing(myView.getDefaultSpacing() / 3);

		turtleSpecificControls.getChildren().addAll(turtleIDs, turtleOptions);

		turtleSpecificControls.setLayoutX(myView.getCanvasDimensions()[0] + 3 * myView.getDefaultSpacing());
		turtleSpecificControls.setLayoutY(3 * myView.getDefaultSpacing());
		turtleSpecificControls.setSpacing(myView.getDefaultSpacing());
		return turtleSpecificControls;
	}

	private VBox createPenToggle() {
		VBox penControls = new VBox();

		HBox penSwitch = createPenSwitch();
		ComboBox<String> penColorSelectionTool = createPenColorSelectionTool();

		penControls.getChildren().addAll(penColorSelectionTool, penSwitch);
		penControls.setSpacing(myView.getDefaultSpacing() / 3);

		return penControls;
	}

	private ComboBox<String> createPenColorSelectionTool() {
		// from
		// http://docs.oracle.com/javafx/2/ui_controls/list-view.htm

		ObservableList<String> data = FXCollections.observableArrayList("black", "white", "chocolate", "salmon", "gold",
				"coral", "darkorchid", "darkgoldenrod", "lightsalmon", "rosybrown", "blue", "blueviolet", "brown");

		penColors = new ComboBox<String>();

		penColors.setItems(data);
		penColors.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectCell();
			}
		});

		penColors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null) {
					model.getTurtleContainer().get(turtleIDs.getSelectionModel().getSelectedItem())
							.changePenColor(Color.web(new_val));
				}
			}
		});

		return penColors;
	}

	private HBox createPenSwitch() {
		HBox penSwitch = new HBox();
		ToggleGroup penSwitchGroup = new ToggleGroup();
		penDown = new RadioButton();
		penDown.setToggleGroup(penSwitchGroup);
		penUp = new RadioButton();
		penUp.setToggleGroup(penSwitchGroup);
		penSwitchGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null
						&& penSwitchGroup.getSelectedToggle() != null) {
					if (penSwitchGroup.getSelectedToggle().equals(penUp)) {
						model.setPenUp(turtleIDs.getSelectionModel().getSelectedItem());
					} else {
						model.setPenDown(turtleIDs.getSelectionModel().getSelectedItem());
					}
				} else {
					// no turtle ID selected
				}
			}
		});

		penSwitch.getChildren().addAll(penDown, penUp);
		turtleIDs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
			public void changed(ObservableValue<? extends Integer> ov, Integer old_val, Integer new_val) {
				if (model.getTurtleContainer().get(new_val).isPenDown()) {
					penSwitchGroup.selectToggle(penDown);
				} else {
					penSwitchGroup.selectToggle(penUp);
				}
			}
		});
		return penSwitch;
	}

	private Button createSendHomeButton() {
		sendHome = new Button();
		sendHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null) {
					model.sendTurtleHome(turtleIDs.getSelectionModel().getSelectedItem());
				} else {
					// no turtle ID selected
				}
			}
		});
		return sendHome;
	}

	private Button createTurtleImageChoose(Stage s) {
		fileChoose = new Button();
		fileChoose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null) {
					File newImageFile = chooseFile(s);
					if (newImageFile != null) {
						model.changeTurtleImage(turtleIDs.getSelectionModel().getSelectedItem(), newImageFile);
					} else {
						// wrong file
					}
				} else {
					// no turtle ID selected
				}
			}
		});
		return fileChoose;
	}

	private void createTurtleIDSelector() {
		turtleIDs = new ComboBox<Integer>();
		turtleIDs.getItems().addAll(model.getTurtleContainer().keySet());
	}

	private File chooseFile(Stage s) {
		FileChooser gifChooser = new FileChooser();
		gifChooser.setTitle("Choose Turtle Image");
		gifChooser.setInitialDirectory(new File(IMAGE_FILE_DIRECTORY));
		File file = gifChooser.showOpenDialog(s);
		if (file != null) {
			String name = file.getName();
			String fileType = name.substring(name.lastIndexOf("."), name.length());
			if (!fileType.equals(IMAGE_EXTENSION)) {
				return null;
			}
			return file;
		}
		return null;
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

	private class LinePredicate implements Predicate {
		@Override
		public boolean test(Object child) {
			return (child instanceof Line);
		}
	}

	public void refreshGUITitles(ResourceBundle resource) {
		helpButton.setText("Help");
		turtleIDs.setPromptText(resource.getString("TurtleIDs"));
		fileChoose.setText(resource.getString("ChooseTurtleImage"));
		backgroundColors.setPromptText(resource.getString("SelectBackground"));
		penColors.setPromptText("Select Pen Color");
		languageDropDown.setPromptText(resource.getString("SetLanguage"));
		turtleCreation.setText(resource.getString("AddNewTurtle"));
		penDown.setText("Pen Down");
		penUp.setText("Pen Up");
		clearLines.setText("Clear All Lines");
		sendHome.setText("Send Home");
	}
}
