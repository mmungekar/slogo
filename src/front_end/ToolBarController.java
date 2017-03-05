package front_end;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import back_end.model.Model;
import front_end.customJavaFxNodes.PopUpHTML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ToolBarController
{
	private ToolBar toolBar;
	private Button newTabButton, fileButton;
	private ComboBox<String> languageDropDown;
	private Model model;
	
	private ObservableList<String> languages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	public static final List<String> RESOURCES_MISC_HELP_HTML = Arrays.asList("/resources/misc/basicCommands.html", "/resources/misc/extendedCommands.html", "/resources/misc/colorPalette.html");

	
	public ToolBarController(Model model, BorderPane inRoot)
	{
		this.model = model;
		toolBar = new ToolBar();
		newTabButton = new Button("New Tab");
		fileButton = new Button("Run File");
		toolBar.getItems().addAll(newTabButton, createLanguageDropDown(), fileButton, createHelpButton());
		inRoot.setTop(toolBar);
	}
	
	public void setFileButton(Consumer<File> r)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Logo","*.logo"));
		
		fileButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				r.accept(fileChooser.showOpenDialog(new Stage()));
			}
		});
	}

	public void setNewTabButton(Runnable r)
	{
		newTabButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				r.run();
			}
		});
	}
	
	private Button createHelpButton()
	{
		Button helpButton = new Button("Help");
		helpButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				PopUpHTML popUpHTML = new PopUpHTML(RESOURCES_MISC_HELP_HTML);
				popUpHTML.setTitle("Help Page");
				popUpHTML.show();
			}
		});
		return helpButton;
	}
	
	private ComboBox<String> createLanguageDropDown() {
		// from stack overflow:
		// http://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
		languageDropDown = new ComboBox<String>(languages);
		languageDropDown.setValue(model.getCurrentLanguage());
		languageDropDown.setMaxWidth(100);
		languageDropDown.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldParam, String newParam) {
				model.setCurrentLanguage(newParam);
			}
		});
		return languageDropDown;
	}

}
