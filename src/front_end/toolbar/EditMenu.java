package front_end.toolbar;

import back_end.model.scene.Model;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EditMenu extends Menu{
	
	private Model myModel;

	public EditMenu(String title, Model model){
		super(title);
		this.myModel = model;
		
		MenuItem addTurtle = new MenuItem("Add New Turtle");
		addTurtle.setOnAction(e -> model.createTurtle(-1));
		
		this.getItems().add(addTurtle);
	}
	
}