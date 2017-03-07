package front_end;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import back_end.model.scene.Model;
import front_end.customJavaFxNodes.ActionButton;
import front_end.customJavaFxNodes.ColorComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TurtleSpecificPanel extends HBox implements Observer{
	public static final String IMAGE_FILE_DIRECTORY = "src/resources/images/";
	public static final String IMAGE_EXTENSION = ".gif";
	public static final String PLEASE_SELECT_PROPER_IMG_FILE = "Please select a %s file for the image of the Turtle";
	public static final String PLEASE_SELECT_A_TURTLE = "Please Select a Turtle";
	
	private ObservableList<String> colors = FXCollections.observableArrayList("white", "black", "chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "rosybrown", "blue", "blueviolet", "brown");

	
	private View myView;
	private Model model;
	
	private ComboBox<Integer> turtleIDs;
	
	private ComboBox<String> penColors;
	private RadioButton penUp;
	private RadioButton penDown;
	private Button sendHome;
	private Button fileChoose;
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	public TurtleSpecificPanel(View view, Model model, Stage s){
		this.myView = view;
		this.model = model;
		model.addObserver(this);
		
		
		VBox turtleOptions = new VBox();

		createTurtleIDSelector();

		Button imageChoose = createTurtleImageChoose(s);
		Button sendHome = createSendHomeButton();
		VBox penControls = createPenToggle();

		turtleOptions.getChildren().addAll(imageChoose, sendHome, penControls);
		turtleOptions.setSpacing(myView.getDefaultSpacing() / 3);

		this.getChildren().addAll(turtleIDs, turtleOptions);

		this.setLayoutX(myView.getCanvasDimensions()[0] + 3 * myView.getDefaultSpacing());
		this.setLayoutY(3 * myView.getDefaultSpacing());
		this.setSpacing(myView.getDefaultSpacing());
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
	
	private void updateTurtleSelection() {
		for (Integer ID : model.getTurtleContainer().keySet()) {
			if (!turtleIDs.getItems().contains(ID)) {
				turtleIDs.getItems().add(ID);
			}
		}
	}
	
	void refreshGUITitles(ResourceBundle resource){
		turtleIDs.setPromptText(resource.getString("TurtleIDs"));
		fileChoose.setText(resource.getString("ChooseTurtleImage"));
		penColors.setPromptText("Select Pen Color");
		penDown.setText("Pen Down");
		penUp.setText("Pen Up");
		sendHome.setText("Send Home");
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		if (obs == model) {
			updateTurtleSelection();
		}
	}
	*/
	

}
