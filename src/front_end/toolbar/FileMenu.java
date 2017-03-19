package front_end.toolbar;

import java.io.File;
import java.util.function.Consumer;

import back_end.exceptions.XMLException;
import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.VariableLibrary;
import back_end.model.scene.Model;
import back_end.model.xml.XMLWriter;
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
	private MenuItem newTabButton, fileButton, storeVarButton, loadVarButton, storeCmdBtn, loadCmdBtn;
	private MenuOptionsList languageOptions;
	private ObservableList<String> languages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	
	
	public FileMenu(String title, Model model){
		super(title);
		this.myModel = model;
		newTabButton = new MenuItem("New Tab");
		fileButton = new MenuItem("Run File");
		storeVarButton = new MenuItem("Store Custom Variable");
		loadVarButton = new MenuItem("Load Custom Variable");
		storeCmdBtn = new MenuItem("Store Custom Command");
		loadCmdBtn = new MenuItem("Load Custom Command");
		languageOptions = new MenuOptionsList("Set Language", languages, "English", lang -> model.setCurrentLanguage(lang));
				
		this.getItems().addAll(newTabButton, fileButton, storeVarButton, loadVarButton, storeCmdBtn, loadCmdBtn, languageOptions);
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
	
	public void setStoreCommandBtn(Consumer<CustomCommandLibrary> r){
		storeCmdBtn.setOnAction((evt) -> {
			r.accept(myModel.getCustomCommandLibrary());
		});
	}
	
	public void setLoadCommandBtn(Consumer<CustomCommandLibrary> r){
		loadCmdBtn.setOnAction((evt) -> {
			r.accept(myModel.getCustomCommandLibrary());
		});
	}
	
	public void setLoadVarButton(Consumer<VariableLibrary> r){
		loadVarButton.setOnAction((evt) -> {
			r.accept(myModel.getUserDefinedVariables());
		});
		
	}
	
	public void setStoreVarButton(Consumer<VariableLibrary> r){
		storeVarButton.setOnAction((evt) -> {
			r.accept(myModel.getUserDefinedVariables());
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
