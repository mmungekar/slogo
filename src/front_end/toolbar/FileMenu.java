package front_end.toolbar;

import java.io.File;
import java.util.function.Consumer;

import back_end.model.scene.Model;
import front_end.customJavaFxNodes.MenuOptionsList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileMenu extends Menu{
	
	private Model myModel;
	private MenuItem newTabButton, fileButton;
	private MenuOptionsList languageOptions;
	private ObservableList<String> languages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	
	
	public FileMenu(String title, Model model){
		super(title);
		this.myModel = model;
		newTabButton = new MenuItem("New Tab");
		fileButton = new MenuItem("Run File");
		languageOptions = new MenuOptionsList("Set Language", languages, "English", lang -> model.setCurrentLanguage(lang));
		MenuItem clear = new MenuItem("Clear");
		clear.setOnAction(e -> {
			model.clearScreen();
		});
		
		this.getItems().addAll(newTabButton, fileButton, languageOptions, clear);
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
				File file = fileChooser.showOpenDialog(new Stage());
				if (file != null){
					r.accept(file);
				}
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
	
}
