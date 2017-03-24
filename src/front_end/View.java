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
 * By Miguel Anderson, Juan Philippe
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

	/**
	 * Constructor: Initializes the borderpane used to organize the GUI
	 * creates the visual components
	 * Takes the language of the model to set the language
	 * gives the Model the home pixel value in order to create a turtle at (0,0) which is the home
	 * 
	 * 
	 * @param tab
	 * @param model
	 */
	public View(Tab tab, Model model)
	{
		BorderPane root = new BorderPane();
		createViewComponents(model, root);
		this.setLanguage(model.getCurrentLanguage());
		
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
				StringBuilder content = new StringBuilder();
				while (scan.hasNextLine())
				{
					String s = scan.nextLine();
					if (s.equals("")) continue;
					
					Scanner stringScanner = new Scanner(s);
					if (!stringScanner.next().equals("#")) content.append(s + " ");
					stringScanner.close();
				}
				
				terminal.submitInput(content.toString());
			}
			catch (FileNotFoundException e)
			{
				terminal.setOutputText(e.getMessage());
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
	
	/** 
	 * sends the listener one more level down (to the terminal) to handle user input
	 * (non-Javadoc)
	 * @see front_end.ViewInterface#setEnterListener(java.util.function.Consumer)
	 */
	public void setEnterListener(Consumer<String> action) {
		terminal.setEnterListener(action);

	}

	private void setLanguage(String language) {
		this.currentLanguage = language;
		refreshGUITitles(ResourceBundle.getBundle(LANGUAGE_DIRECTORY + this.currentLanguage));
	}

	private void refreshGUITitles(ResourceBundle resource) {
		terminal.refreshGUITitles(resource);
		((UserDefinedEntries) userDefinedEntries).refreshGUITitles(resource);
	}


	/**
	 * Sends output message to terminal
	 * @param message
	 */
	public void setOutput(String message) {
		terminal.setOutputText(message);
	}
	

	
	/**
	 * Internal shortcut to submitting an input
	 * 
	 * @param input - to be submitted
	 */
	public void submitInput(String input) {
		terminal.setText(input);
		terminal.submitInput();
	}

	
	/**
	 * Setting new tab button in the toolbar
	 * 
	 * @param r - action of the NewTabButton
	 */
	public void setNewTabButton(Runnable r)
	{
		toolBar.setNewTabButton(r);
	}


}
