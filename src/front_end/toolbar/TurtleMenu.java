package front_end.toolbar;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
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
import javafx.scene.image.ImageView;
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
	
	private MenuOptionsList turtleStatus;
	private Menu turtleImages;
		
	public TurtleMenu(String title, Model model){
		super(title);
		this.model = model;
		model.addObserver(this);
		turtleImages = new Menu("IMAGE EXTENSION");
		updateTurtleImageExtension(model, turtleImages);
		
		MenuItem addTurtle = addTurtle(model);
		turtleStatus = turtleStatus(model);
		MenuItem activeMessage = activeMessage();
		MenuItem sendHome = sendHome(model);
		MenuItem clear = clear(model);
		MenuItem remove = remove(model);
		Menu visibility = visibility(model);
		Menu penProperties = penProperties(model);
		MenuItem image = image(model);
		this.getItems().addAll(turtleImages, addTurtle, turtleStatus, activeMessage, sendHome, clear, remove, visibility, penProperties, image);
	}
	
	private void updateTurtleImageExtension(Model model2, Menu turtleImages2) {
		turtleImages2.getItems().clear();
		List<Integer> allTurtleIDs = model2.getTurtleMaster().getAllTurtleIDs();
		for(Integer id : allTurtleIDs){
			MenuItem menuItem = createImageMenuItem(model2, id);
			turtleImages2.getItems().add(menuItem);
		}
	}

	private MenuItem createImageMenuItem(Model model2, Integer id) {
		Turtle currentTurtle = model2.getTurtleMaster().getTurtle(id);
		ImageView imageView = new ImageView(currentTurtle.getImageView().getImage());
		MenuItem menuItem = new MenuItem(id.toString(), imageView);
		menuItem.setOnAction(e -> {
			getAndSetNewImageOnTurtle(currentTurtle);
			menuItem.setGraphic(new ImageView(currentTurtle.getImageView().getImage()));
		});
		return menuItem;
	}
	
	private void getAndSetNewImageOnTurtle(Turtle turtle) {
		File newImageFile = chooseFile();
		if (newImageFile != null) {
			turtle.changeImage(newImageFile.getName());
		}
	}

	private MenuOptionsList turtleStatus(Model model) {
		ObservableList<String> turtleIDs = createTurtleIdObservable(model);
		return new MenuOptionsList("Print Turtle Status", turtleIDs, null, turtleIDasString -> {
			model.getTurtleMaster().printStatus(Integer.parseInt(turtleIDasString));	
		});
	}
	private ObservableList<String> createTurtleIdObservable(Model model) {
		ObservableList<String> turtleIDs = FXCollections.observableArrayList(model.getTurtleMaster().getAllTurtleIDs().stream().map( e -> e.toString() ).collect( Collectors.toList() ) );
		return turtleIDs;
	}
	private MenuItem image(Model model) {
		MenuItem image = new MenuItem("Select New Turtle Image");
		
		image.setOnAction(e -> {
			File newImageFile = chooseFile();
			if (newImageFile != null) {
				model.getTurtleMaster().operateOnTurtle(turtle -> turtle.changeImage(newImageFile.getName()));
			} else {
				//myView.setOutput(String.format(PLEASE_SELECT_PROPER_IMG_FILE, IMAGE_EXTENSION));
			}
		});
		return image;
	}
	private Menu penProperties(Model model) {
		Menu penProperties = new Menu("Pen Properties");
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
		return penProperties;
	}
	private Menu visibility(Model model) {
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
		return visibility;
	}
	private MenuItem remove(Model model) {
		MenuItem remove = new MenuItem("Remove from screen");
		remove.setOnAction(e -> {
			model.getTurtleMaster().removeActiveTurtles();
		});
		return remove;
	}
	private MenuItem clear(Model model) {
		MenuItem clear = new MenuItem("Clear");
		clear.setOnAction(e -> {
			model.getTurtleMaster().operateOnTurtle(turtle -> {
				turtle.dontDrawLine();
				return turtle.setPosition(model.getHome());
			});
		});
		return clear;
	}
	private MenuItem sendHome(Model model) {
		MenuItem sendHome = new MenuItem("Send to Home");
		sendHome.setOnAction(e -> {
			model.getTurtleMaster().operateOnTurtle(turtle -> turtle.setPosition(model.getHome()));
		});
		return sendHome;
	}
	private MenuItem activeMessage() {
		MenuItem activeMessage = new MenuItem("The following only effect active turtles");
		activeMessage.setDisable(true);
		return activeMessage;
	}
	private MenuItem addTurtle(Model model) {
		MenuItem addTurtle = new MenuItem("Add New Turtle");
		addTurtle.setOnAction(e -> model.getTurtleMaster().breedTurtle(-1));
		return addTurtle;
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
	
	@Override
	public void update(Observable obs, Object obj) {
		if (obs == model) {
			updateTurtleSelection();
		}
	}
	private void updateTurtleSelection() {
		turtleStatus.refreshOptions(createTurtleIdObservable(model));
		updateTurtleImageExtension(model, turtleImages);
	}
	
	
}