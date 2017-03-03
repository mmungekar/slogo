package front_end;

import java.util.ResourceBundle;
import java.util.function.Consumer;

import back_end.model.Model;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View implements ViewInterface {
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 1000;
	public static final int CANVAS_HEIGHT = 400;
	public static final int CANVAS_WIDTH = 600;
	public static final Color BACKGROUND_COLOR = Color.GREY;
	public static final int DEFAULT_SPACING = 30;
	public static final Point2D HOME = new Point2D(CANVAS_WIDTH / 2 + DEFAULT_SPACING,
			CANVAS_HEIGHT / 2 + DEFAULT_SPACING);
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";

	private Consumer<String> onMessageReceivedHandler;
	//private Consumer<String> languageHandler;

	
	private Terminal terminal;
	private Rectangle canvas;
	private VBox sideBar;
	private TabPane userDefinedEntries;
	
	private Color canvasColor = Color.WHITE;
	private String currentLanguage;

	public View(Tab tab, Model model) {
		Group root = new Group();
		createViewComponents(model, root);
		
		this.setLanguage(model.getCurrentLanguage());

		//Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, BACKGROUND_COLOR);

		
		tab.setContent(root);
		model.setHome(HOME); // also creates first turtle
	}

	private void createViewComponents(Model model, Group root) {
		createCanvas(model, root);
		createTerminal(root);
		createUserDefinedEntries(model, root);
		createSideBar(model, root);
		
	}

	private void createSideBar(Model model, Group root) {
		sideBar = new SideBar(model, root, this);
		sideBar.setLayoutX(CANVAS_WIDTH + 3 * DEFAULT_SPACING);
		sideBar.setLayoutY(DEFAULT_SPACING);
		sideBar.setSpacing(DEFAULT_SPACING / 2);
		root.getChildren().add(sideBar);
	}

	private void createUserDefinedEntries(Model model, Group root) {
		userDefinedEntries = new UserDefinedEntries(model, this);
		userDefinedEntries.setLayoutY(WINDOW_HEIGHT - 350);
		userDefinedEntries.setLayoutX(CANVAS_WIDTH + 3 * DEFAULT_SPACING);
		userDefinedEntries.setPrefHeight(350 - DEFAULT_SPACING);
		root.getChildren().add(userDefinedEntries);
	}

	private void createCanvas(Model model, Group root) {
		canvas = new Canvas(model, root);
		canvas.setX(DEFAULT_SPACING);
		canvas.setY(DEFAULT_SPACING);
		canvas.setWidth(CANVAS_WIDTH);
		canvas.setHeight(CANVAS_HEIGHT);
		canvas.setFill(canvasColor);
		root.getChildren().add(canvas);
	}

	private void createTerminal(Group root) {
		terminal = new Terminal();
		terminal.setSpacing(DEFAULT_SPACING);
		terminal.setLayoutY(WINDOW_HEIGHT - 350);
		terminal.setLayoutX(40);
		root.getChildren().add(terminal);
	}
	
	public void setEnterListener(Consumer<String> action) {
		this.onMessageReceivedHandler = action;
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
		((SideBar) sideBar).refreshGUITitles(resource);
		((UserDefinedEntries) userDefinedEntries).refreshGUITitles(resource);
	}

	/*
	public void setLanguageChangeListener(final Consumer<String> action) {
		this.languageHandler = action;

	}
	*/

	public void changeBackgroundColor(Color newColor) {
		canvas.setFill(newColor);
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


}
