package back_end.model.scene;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import back_end.commands.custom.CustomCommand;
import back_end.commands.custom.CustomVariable;
import back_end.exceptions.VariableNotFoundException;

import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.VariableLibrary;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Model extends Observable {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "turtle.gif";
	public static final String DEFAULT_LANGUAGE = "English";
	public static final int DEFAULT_BACKGROUND_COLOR_INDEX = 0;
	public static final List<String> DEFAULT_COLOR_HTML_NAMES = Arrays.asList("white", "black", "blue", "red", "green",
			"yellow", "orange");

	private Map<Integer, Turtle> turtleContainer;

	private String currentLanguage = DEFAULT_LANGUAGE;
	private Color backgroundColor;
	private Point2D home;
	private boolean clear;
	public VariableLibrary mGlobalVariableLibrary;
	public VariableLibrary mLocalVariableLibrary;
	public CustomCommandLibrary mCustomCommandLibrary;
	private HashMap<Integer, Color> colorContainer;

	public Model() {
		turtleContainer = new HashMap<Integer, Turtle>();
		setBackgroundColor(Color.WHITE);
		mGlobalVariableLibrary = new VariableLibrary();
		mLocalVariableLibrary = new VariableLibrary();
		mCustomCommandLibrary = new CustomCommandLibrary();
	}

	public void setColorRGB(int index, int r, int g, int b) {
		colorContainer.put(index, Color.rgb(r, g, b));

	}

	public CustomCommandLibrary getCustomCommandLibrary() {
		return mCustomCommandLibrary;
	}

	private void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
	}

	public void addCustomCommand(String name, CustomCommand command) {
		mCustomCommandLibrary.put(name, command);
		setChangedAndNotifyObservers();
	}

	public void setPos(int ID, double inX, double inY) {
		turtleContainer.get(ID).setPosition(inX, inY);
		setChangedAndNotifyObservers();
	}

	public void setAngle(int ID, double inAngle) {
		turtleContainer.get(ID).setAngle(inAngle);
		setChangedAndNotifyObservers();
	}

	public void setPenDown(int ID) {
		turtleContainer.get(ID).setPenDown();
		// setChangedAndNotifyObservers();
	}

	public void setPenUp(int ID) {
		turtleContainer.get(ID).setPenUp();
		// setChangedAndNotifyObservers();
	}

	public void setVisible(int ID) {
		turtleContainer.get(ID).setVisible(true);
	}

	public void setInVisible(int ID) {
		turtleContainer.get(ID).setVisible(false);
	}

	public boolean isVisible(int ID) {
		return turtleContainer.get(ID).isVisible();
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		setChangedAndNotifyObservers();
	}

	public void setBackgroundColor(int index) {
		setBackgroundColor(colorContainer.get(index));
	}

	public void changeTurtleImage(Integer ID, File newImageFile) {
		Image newTurtleImage = new Image(
				getClass().getClassLoader().getResourceAsStream(IMAGE_DIRECTORY + newImageFile.getName()));
		turtleContainer.get(ID).changeImage(newTurtleImage);
		setChangedAndNotifyObservers();
	}

	public void sendTurtleHome(int ID) {
		turtleContainer.get(ID).setPosition(home);
		turtleContainer.get(ID).dontDrawLine();
		setChangedAndNotifyObservers();

	}

	public void createTurtle() {
		Integer nextID = Collections.max(turtleContainer.keySet()) + 1;
		turtleContainer.put(nextID, new Turtle(getDefaultTurtleImage(), home));
		setChangedAndNotifyObservers();
	}

	public void setHome(Point2D home) {
		this.home = home;
		// create first turtle once home is set
		turtleContainer.put(0, new Turtle(getDefaultTurtleImage(), home));
		setChangedAndNotifyObservers();
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}

	public boolean isClear() {
		return this.clear;
	}

	public double getX(int ID) {
		return turtleContainer.get(ID).getCenterPosition().getX();
	}

	public double getY(int ID) {
		return turtleContainer.get(ID).getCenterPosition().getY();
	}

	public double getAngle(int ID) {
		return turtleContainer.get(ID).getAngle();
	}

	public boolean isPenDown(int ID) {
		return turtleContainer.get(ID).isPenDown();
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Map<Integer, Turtle> getTurtleContainer() {
		return turtleContainer;
	}

	public Point2D getHome() {
		return this.home;
	}

	private Image getDefaultTurtleImage() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}

	private void createDefaultColors() {
		colorContainer = new HashMap<Integer, Color>();
		for (int i = 0; i < DEFAULT_COLOR_HTML_NAMES.size(); i++) {
			colorContainer.put(i, Color.web(DEFAULT_COLOR_HTML_NAMES.get(i)));
		}
		backgroundColor = colorContainer.get(DEFAULT_BACKGROUND_COLOR_INDEX);
	}

	@Override
	public String toString() {
		return ("X: " + (this.getX(0) - home.getX()) + " Y: " + -1 * (this.getY(0) - home.getY()) + " Angle: "
				+ this.getAngle(0));
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
		setChangedAndNotifyObservers();
	}

	public void updateVariable(String name, double value) {
		mGlobalVariableLibrary.updateVariable(name, value);
		setChangedAndNotifyObservers();
	}

	public Collection<CustomVariable> getUserDefinedVariables() {
		List<CustomVariable> vars = new ArrayList<>();
		for (String key : mGlobalVariableLibrary.keySet()) {
			CustomVariable var = new CustomVariable(key, mGlobalVariableLibrary.get(key));
		}
		return vars;
	}

	public Collection<String> getUserDefinedCommands() {
		return mCustomCommandLibrary.keySet();
	}

	public boolean hasCustomVariable(String parameter) {
		return mLocalVariableLibrary.hasVariable(parameter);
	}

	public Double retrieveCustomVariable(String nodeName) throws VariableNotFoundException {
		return mLocalVariableLibrary.retrieveVariable(nodeName);
	}

}