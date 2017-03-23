package front_end.toolbar;

import java.io.File;
import java.util.function.Consumer;

import back_end.model.scene.Model;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;


public class ToolBarController
{
	private MenuBar toolBar;
	private FileMenu file;
	private EditMenu edit;
	private ViewMenu view;
	private TurtleMenu turtle;
	private HelpMenu help;
	
	private Model model;
	
	public ToolBarController(Model model, BorderPane inRoot)
	{
		this.model = model;
		
		// https://blog.idrsolutions.com/2014/03/create-stacked-menus-in-javafx/
		
		toolBar = new MenuBar();
		
		file = new FileMenu("File", model);
		edit = new EditMenu("Edit", model);
		view = new ViewMenu("View", model);
		turtle = new TurtleMenu("Turtle Controls", model);
		help = new HelpMenu("Help");
		
		toolBar.getMenus().addAll(file, edit, view, turtle, help);
		
		inRoot.setTop(toolBar);
	}



	public void setFileButton(Consumer<File> r) {
		file.setFileButton(r);
	}



	public void setNewTabButton(Runnable r) {
		file.setNewTabButton(r);
		
	}
}
