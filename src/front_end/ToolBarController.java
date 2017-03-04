package front_end;

import back_end.model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class ToolBarController
{
	private ToolBar toolBar;
	private Button newTabButton;
	private ComboBox<String> languageDropDown;
	private Model model;
	private ObservableList<String> languages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	
	public ToolBarController(Model model, BorderPane inRoot)
	{
		this.model = model;
		toolBar = new ToolBar();
		newTabButton = new Button("New Tab");
		toolBar.getItems().addAll(newTabButton, createLanguageDropDown());
		inRoot.setTop(toolBar);
	}
	
	public void setNewTabButton(Runnable r)
	{
		newTabButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0) {
				r.run();
			}
		});
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
