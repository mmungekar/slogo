package front_end;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import back_end.ModelState;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SideBar {
	public static final String IMAGE_FILE_DIRECTORY = "src/resources/images/";
	public static final String IMAGE_EXTENSION = ".gif";
	private List<String> languages = Arrays.asList(
			new String[] { "Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish" });

	private View myView;
	private ComboBox<Integer> turtleIDs;
	private ModelState modelState;
	
	public SideBar(Stage s, ModelState modelState, Group root, View view) {
		this.myView = view;
		this.modelState = modelState;
		VBox sideButtons = new VBox();

		sideButtons.getChildren().addAll(createLanguageDropDown(), createBackgroundColorSelectionTool(),
				createClearLinesButton(root), addTurtleButton(), createTurtleSpecficCommands(s));

		sideButtons.setLayoutX(myView.getCanvasDimensions()[0] + 3 * myView.getDefaultSpacing());
		sideButtons.setLayoutY(myView.getDefaultSpacing());
		sideButtons.setSpacing(myView.getDefaultSpacing() / 2);

		root.getChildren().add(sideButtons);
	}
	
	private ComboBox<String> createLanguageDropDown() {
		// from stack overflow:
		// http://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
		ObservableList<String> obsNames = FXCollections.observableArrayList(languages);
		ComboBox<String> languageDropDown = new ComboBox<String>(obsNames);
		languageDropDown.setMaxWidth(200);
		languageDropDown.setPromptText(myView.getCurrentResource().getString("SetLanguage"));
		languageDropDown.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldParam, String newParam) {
				myView.setLanguage(newParam);
			}
		});
		return languageDropDown;
	}
	
	private ComboBox<String> createBackgroundColorSelectionTool() {
		// from
		// http://docs.oracle.com/javafx/2/ui_controls/list-view.htm

		ObservableList<String> data = FXCollections.observableArrayList("white", "chocolate", "salmon", "gold", "coral",
				"darkorchid", "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue", "blueviolet", "brown");

		ComboBox<String> backgroundColors = new ComboBox<String>();

		backgroundColors.setItems(data);
		backgroundColors.setPromptText(myView.getCurrentResource().getString("SelectBackground"));
		backgroundColors.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectCell();
			}
		});

		backgroundColors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				myView.changeBackgroundColor(Color.web(new_val));
				modelState.setBackgroundColor(Color.web(new_val));
			}
		});

		return backgroundColors;
	}
	
	private Node createClearLinesButton(Group root) {
		Button clearLines = new Button("Clear Lines");
		clearLines.setOnAction(event -> clearLines(root));
		return clearLines;
	}

	private void clearLines(Group root) {
		root.getChildren().removeIf(new LinePredicate());
	}
	
	private Button addTurtleButton() {
		Button turtleCreation = new Button(myView.getCurrentResource().getString("AddNewTurtle"));
		turtleCreation.setOnAction(event -> {
			createTurtle();
			updateTurtleSelection();
		});
		return turtleCreation;
	}

	private void createTurtle() {
		modelState.createTurtle();
	}

	private void updateTurtleSelection() {
		turtleIDs.getItems().clear();
		turtleIDs.getItems().addAll(modelState.getTurtleContainer().keySet());
	}
	
	private VBox createTurtleSpecficCommands(Stage s) {
		VBox turtleSpecificControls = new VBox();

		createTurtleIDSelector();

		Button imageChoose = createTurtleImageChoose(s);
		// TODO pen toggle switch
		// TODO go home
		

		turtleSpecificControls.getChildren().addAll(turtleIDs, imageChoose);

		turtleSpecificControls.setLayoutX(myView.getCanvasDimensions()[0] + 3 * myView.getDefaultSpacing());
		turtleSpecificControls.setLayoutY(3 * myView.getDefaultSpacing());
		return turtleSpecificControls;
	}


	private Button createTurtleImageChoose(Stage s) {
		Button fileChoose = new Button(myView.getCurrentResource().getString("ChooseTurtleImage"));
		fileChoose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (turtleIDs.getSelectionModel().getSelectedItem() != null) {
					File newImageFile = chooseFile(s);
					if (newImageFile != null) {
						modelState.changeTurtleImage(turtleIDs.getSelectionModel().getSelectedItem(), newImageFile);
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
		turtleIDs.setPromptText(myView.getCurrentResource().getString("TurtleIDs"));
		turtleIDs.getItems().addAll(modelState.getTurtleContainer().keySet());
	}

	private File chooseFile(Stage s) {
		FileChooser xmlChooser = new FileChooser();
		xmlChooser.setTitle(myView.getCurrentResource().getString("ChooseTurtleImage"));
		xmlChooser.setInitialDirectory(new File(IMAGE_FILE_DIRECTORY));
		File file = xmlChooser.showOpenDialog(s);
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
	
	private class LinePredicate implements Predicate{
		@Override
		  public boolean test(Object child){
			  return (child instanceof Line);
		  }
	}
}
