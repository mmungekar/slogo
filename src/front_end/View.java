package front_end;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Consumer;

import back_end.model.scene.Model;
import front_end.toolbar.ToolBarController;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * By Miguel Anderson
 *
 */
public class View implements ViewInterface {
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 1000;
	public static final int CANVAS_HEIGHT = 400;
	public static final int CANVAS_WIDTH = 600;
	public static final Color BACKGROUND_COLOR = Color.GREY;
	public static final int DEFAULT_SPACING = 30;
	public static final Point2D HOME = new Point2D(CANVAS_WIDTH / 2,
			CANVAS_HEIGHT / 2);
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";

	
	private Terminal terminal;
	private Canvas canvas;
	private ToolBarController toolBar;
	private TabPane userDefinedEntries;
	private String currentLanguage;

	public View(Tab tab, Model model)
	{
		BorderPane root = new BorderPane();
		createViewComponents(model, root);
		setLanguage(model.getCurrentLanguage());
		
		tab.setContent(root);
		model.setHome(HOME); // also creates first turtle
	}

	private void createViewComponents(Model model, BorderPane root)
	{
		createCanvas(model, root);
		createTerminal(root);
		createUserDefinedEntries(model, root);
		createToolBar(model, root);
	}
	//TODO: HANDLE EXCEPTION HERE
	private void createToolBar(Model model, BorderPane root)
	{
		toolBar = new ToolBarController(model, root);
		toolBar.setFileButton((File f) ->
		{
			Scanner scan;
			try
			{
				scan = new Scanner(f);
				scan.useDelimiter("\\Z");  
				String content = scan.next();
				terminal.submitInput(content);
			}
			catch (FileNotFoundException e)
			{
				terminal.setOutputText("File Not Found");
			}
		});
	}

	private void createUserDefinedEntries(Model model, BorderPane root) {
		userDefinedEntries = new UserDefinedEntries(model, this);
		root.setRight(userDefinedEntries);
	}

	private void createCanvas(Model model, BorderPane root) {
		canvas = new Canvas(model);
		canvas.setWidth(CANVAS_WIDTH);
		canvas.setHeight(CANVAS_HEIGHT);
		root.setCenter(canvas.getRoot());
	}

	private void createTerminal(BorderPane root) {
		terminal = new Terminal();
		BorderPane.setAlignment(terminal, Pos.BOTTOM_RIGHT);
		root.setBottom(terminal);
	}
	
	public void setEnterListener(Consumer<String> action) {
		terminal.setEnterListener(action);

	}

	public String getLanguage() {
		return this.currentLanguage;
	}

	void setLanguage(String language) {
		this.currentLanguage = language;
		refreshGUITitles(ResourceBundle.getBundle(LANGUAGE_DIRECTORY + this.currentLanguage));
	}

	private void refreshGUITitles(ResourceBundle resource) {
		terminal.refreshGUITitles(resource);
		((UserDefinedEntries) userDefinedEntries).refreshGUITitles(resource);
	}


	public void setOutput(String message) {
		terminal.setOutputText(message);
	}
	
	double[] getCanvasDimensions(){
		return new double[]{CANVAS_WIDTH, CANVAS_HEIGHT};
	}
	
	double getDefaultSpacing(){
		return DEFAULT_SPACING;
	}

	public void submitInput(String item) {
		terminal.setText(item);
		terminal.submitInput();
	}

	public void setNewTabButton(Runnable r)
	{
		toolBar.setNewTabButton(r);
	}


}
