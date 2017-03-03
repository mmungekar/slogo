package front_end;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.function.Predicate;


import back_end.model.Model;
import back_end.model.Turtle;
import front_end.customJavaFxNodes.ActionButton;
import front_end.customJavaFxNodes.ColorComboBox;
import front_end.customJavaFxNodes.PopUpHTML;
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

public class SideBar extends VBox implements Observer{
	public static final String PLEASE_SELECT_PROPER_IMG_FILE = "Please select a %s file for the image of the Turtle";
	public static final String PLEASE_SELECT_A_TURTLE = "Please Select a Turtle";
	public static final List<String> RESOURCES_MISC_HELP_HTML = Arrays.asList("/resources/misc/basicCommands.html", "/resources/misc/extendedCommands.html");
	public static final String IMAGE_FILE_DIRECTORY = "src/resources/images/";
	public static final String IMAGE_EXTENSION = ".gif";
	private ObservableList<String> languages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	
	private ObservableList<String> colors = FXCollections.observableArrayList("white", "black", "chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "rosybrown", "blue", "blueviolet", "brown");

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
		model.addObserver(this);

		this.getChildren().addAll(createHelpButton(), createLanguageDropDown(), createBackgroundColorSelectionTool(),
				createClearLinesButton(root), addTurtleButton(), createTurtleSpecficCommands(s));

	}

	private Node createHelpButton() {
		helpButton = new ActionButton(event -> displayHelpScreen());
		return helpButton;
	}

	private void displayHelpScreen() {
		PopUpHTML popUpHTML = new PopUpHTML(RESOURCES_MISC_HELP_HTML);
		popUpHTML.setTitle("Help Page");
		popUpHTML.show();
		
	}

	private ComboBox<String> createLanguageDropDown() {
		// from stack overflow:
		// http://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
		languageDropDown = new ComboBox<String>(languages);
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

	private Node createClearLinesButton(Group root) {
		clearLines = new ActionButton(event -> clearLines(root));
		return clearLines;
	}

	private void clearLines(Group root) {
		root.getChildren().removeIf(new LinePredicate());
	}

	private Button addTurtleButton() {
		turtleCreation = new ActionButton(event -> {
			model.createTurtle();
			updateTurtleSelection();
		});
		return turtleCreation;
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
		penColors = new ColorComboBox(colors);

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
	
	private ComboBox<String> createBackgroundColorSelectionTool() {
		backgroundColors = new ColorComboBox(colors);

		backgroundColors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				myView.changeBackgroundColor(Color.web(new_val));
				model.setBackgroundColor(Color.web(new_val));
			}
		});

		return backgroundColors;
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
					askForSelection();
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
		sendHome = new ActionButton(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null) {
					model.sendTurtleHome(turtleIDs.getSelectionModel().getSelectedItem());
				} else {
					askForSelection();
				}
			}
		});
		return sendHome;
	}

	protected void askForSelection() {
		myView.setOutput(PLEASE_SELECT_A_TURTLE);
		
	}

	private Button createTurtleImageChoose(Stage s) {
		fileChoose = new ActionButton(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null) {
					File newImageFile = chooseFile(s);
					if (newImageFile != null) {
						model.changeTurtleImage(turtleIDs.getSelectionModel().getSelectedItem(), newImageFile);
					} else {
						myView.setOutput(String.format(PLEASE_SELECT_PROPER_IMG_FILE, IMAGE_EXTENSION));
					}
				} else {
					askForSelection();
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

	private class LinePredicate implements Predicate {
		@Override
		public boolean test(Object child) {
			return (child instanceof Line);
		}
	}

	void refreshGUITitles(ResourceBundle resource) {
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

	@Override
	public void update(Observable obs, Object obj) {
		if (obs == model) {
			updateTurtleSelection();
		}
	}
}
