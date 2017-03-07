package front_end.toolbar;

import back_end.model.scene.Model;
import front_end.customJavaFxNodes.MenuOptionsList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.paint.Color;

public class ViewMenu extends Menu {
	
	private ObservableList<String> colors = FXCollections.observableArrayList("white", "black", "chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "rosybrown", "blue", "blueviolet", "brown");

	
	private Menu backgroundOptions;
	private Model myModel;
	
	public ViewMenu(String title, Model model){
		super(title);
		this.myModel = model;
		
		backgroundOptions = new MenuOptionsList("Set Background Color", colors, "White", color -> model.setBackgroundColor(Color.web(color)));
		
		this.getItems().add(backgroundOptions);
		
	}

}
