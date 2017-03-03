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

public class SideBar extends VBox{
	public static final List<String> RESOURCES_MISC_HELP_HTML = Arrays.asList("/resources/misc/basicCommands.html", "/resources/misc/extendedCommands.html");
	private ObservableList<String> languages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	
	private ObservableList<String> colors = FXCollections.observableArrayList("white", "black", "chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "rosybrown", "blue", "blueviolet", "brown");

	private View myView;
	private Model model;

	// all objects that have titles
	private Button helpButton;
	private ComboBox<String> languageDropDown;
	private ComboBox<String> backgroundColors;
	private Button clearLines;
	private Button turtleCreation;

	public SideBar(Stage s, Model model, Group root, View view) {
		this.myView = view;
		this.model = model;

		this.getChildren().addAll(createHelpButton(), createLanguageDropDown(), createBackgroundColorSelectionTool(),
				createClearLinesButton(root), addTurtleButton()
				//, new TurtleSpecificPanel(view, model, s)
				);

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
			model.createTurtle(-1);
		});
		return turtleCreation;
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

	private class LinePredicate implements Predicate {
		@Override
		public boolean test(Object child) {
			return (child instanceof Line);
		}
	}

	void refreshGUITitles(ResourceBundle resource) {
		helpButton.setText("Help");
		backgroundColors.setPromptText(resource.getString("SelectBackground"));
		languageDropDown.setPromptText(resource.getString("SetLanguage"));
		turtleCreation.setText(resource.getString("AddNewTurtle"));
		clearLines.setText("Clear All Lines");
		
		
	}


}
