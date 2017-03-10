package front_end.toolbar;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import back_end.model.scene.Model;
import front_end.View;
import front_end.customJavaFxNodes.ActionButton;
import front_end.customJavaFxNodes.ColorComboBox;
import front_end.customJavaFxNodes.MenuOptionsList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TurtleMenu extends Menu implements Observer{
	public static final String IMAGE_FILE_DIRECTORY = "src/resources/images/";
	public static final String IMAGE_EXTENSION = ".gif";
	public static final String PLEASE_SELECT_PROPER_IMG_FILE = "Please select a %s file for the image of the Turtle";
	public static final String PLEASE_SELECT_A_TURTLE = "Please Select a Turtle";
	
	private ObservableList<String> colors = FXCollections.observableArrayList("white", "black", "chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "rosybrown", "blue", "blueviolet", "brown");

	
	
	private Model model;
	
	private Menu penColorOptions;
	private Menu penStatusOption;
	
	/*
	private ComboBox<Integer> turtleIDs;
	
	private ComboBox<String> penColors;
	private RadioButton penUp;
	private RadioButton penDown;
	private Button sendHome;
	private Button fileChoose;
	*/
	
	
	public TurtleMenu(String title, Model model){
		super(title);
		this.model = model;
		model.addObserver(this);
		
		MenuItem addTurtle = new MenuItem("Add New Turtle");
		addTurtle.setOnAction(e -> model.getTurtleMaster().breedTurtle(-1));
		
		MenuItem activeMessage = new MenuItem("The following only effect active turtles");
		activeMessage.setDisable(true);
		
		MenuItem sendHome = new MenuItem("Send to Home");
		
		sendHome.setOnAction(e -> {
			model.getTurtleMaster().operateOnTurtle(turtle -> turtle.setPosition(model.getHome()));
		});
		
		MenuItem clear = new MenuItem("Clear");
		clear.setOnAction(e -> {
			model.getTurtleMaster().operateOnTurtle(turtle -> {
				turtle.dontDrawLine();
				return turtle.setPosition(model.getHome());
			});
		});
		
		
		MenuItem remove = new MenuItem("Remove from screen");
		
		remove.setOnAction(e -> {
			model.getTurtleMaster().removeActiveTurtles();
		});
		
		
		Menu visibility = new Menu("Visibility");
		
		MenuItem show = new MenuItem("Show");
		
		show.setOnAction(e -> {
			model.getTurtleMaster().operateOnTurtle(turtle -> turtle.setVisible(true));
		});
		
		MenuItem hide = new MenuItem("Hide");
		
		hide.setOnAction(e -> {
			model.getTurtleMaster().operateOnTurtle(turtle -> turtle.setVisible(false));
		});
		
		
		visibility.getItems().addAll(show, hide);
		
		
		Menu penProperties = new Menu("Pen Properties");
		
		
		MenuItem image = new MenuItem("Select New Turtle Image");
		
		image.setOnAction(e -> {
			File newImageFile = chooseFile();
			if (newImageFile != null) {
				model.getTurtleMaster().operateOnTurtle(turtle -> turtle.changeImage(newImageFile.toString()));
			} else {
				//myView.setOutput(String.format(PLEASE_SELECT_PROPER_IMG_FILE, IMAGE_EXTENSION));
			}
		});
		
		
		penColorOptions = new MenuOptionsList("Set Pen Color", colors, "Black", color -> {
			model.getTurtleMaster().operateOnTurtle(turtle -> turtle.setPenColor(Color.web(color)));
			});
		penStatusOption = new MenuOptionsList("Set Pen", FXCollections.observableArrayList("Up", "Down"), "Down", selection -> {
			if (selection.equals("Down")){
				model.getTurtleMaster().operateOnTurtle(turtle -> turtle.setPen(true));
			} else {
				model.getTurtleMaster().operateOnTurtle(turtle -> turtle.setPen(false));
			}
		});
		
		
		penProperties.getItems().addAll(penStatusOption, penColorOptions);
		//MenuItem imageChoose = createTurtleImageChoose();
		//MenuItem sendHome = createSendHomeButton();
		//MenuItem penControls = createPenToggle();

		this.getItems().addAll(addTurtle, activeMessage, sendHome, clear, remove, visibility, penProperties, image);
	}
	
	private File chooseFile() {
		FileChooser gifChooser = new FileChooser();
		gifChooser.setTitle("Choose Turtle Image");
		gifChooser.setInitialDirectory(new File(IMAGE_FILE_DIRECTORY));
		File file = gifChooser.showOpenDialog(new Stage());
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
	
	/*
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
	*/
	@Override
	public void update(Observable obs, Object obj) {
		if (obs == model) {
			//supdateTurtleSelection();
		}
	}
	
	

}
