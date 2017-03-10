package front_end.toolbar;

import back_end.model.scene.Model;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EditMenu extends Menu{
	
	private Model myModel;

	public EditMenu(String title, Model model){
		super(title);
		this.myModel = model;
		
		MenuItem clearVariables = new MenuItem("Clear Custom Variables");
		
		clearVariables.setOnAction(e -> model.getCustomMaster().clearVariables());
		
		MenuItem clearCommands = new MenuItem("Clear Custom Commands");
		
		clearCommands.setOnAction(e -> model.getCustomMaster().clearCommands());
		
		
		this.getItems().addAll(clearCommands, clearVariables);
	}
	
}