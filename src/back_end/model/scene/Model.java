package back_end.model.scene;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import back_end.CustomVariable;
import back_end.commands.CustomCommand;
import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.VariableLibrary;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Model extends Observable {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "turtle.gif";
	public static final String DEFAULT_LANGUAGE = "English";

	private Map<Integer, Turtle> turtleContainer;
	private HashMap<String, CustomCommand> customCommands;
	private String currentLanguage = DEFAULT_LANGUAGE;
	private Color backgroundColor;
	private Point2D home;
	private boolean clear;
	public VariableLibrary mGlobalVariableLibrary;
	public VariableLibrary mLocalVariableLibrary;
	public CustomCommandLibrary mCustomCommandLibrary;

	public Model()
	{
		turtleContainer = new HashMap<Integer, Turtle>();
		customCommands = new HashMap<String, CustomCommand>();
		setBackgroundColor(Color.WHITE);
		mGlobalVariableLibrary = new VariableLibrary();
		mLocalVariableLibrary = new VariableLibrary();
		mCustomCommandLibrary = new CustomCommandLibrary();
	}

	public HashMap<String,CustomCommand> getCustomCommands(){
		return customCommands;
	}
	
	private void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
	}
	
	public void addCustomCommand(String name, CustomCommand command)
	{
		customCommands.put(name, command);
		setChangedAndNotifyObservers();
	}

	public void setPos(int ID, double inX, double inY){
		turtleContainer.get(ID).setPosition(inX, inY);
		setChangedAndNotifyObservers();
	}

	public void setAngle(int ID, double inAngle) {
		turtleContainer.get(ID).setAngle(inAngle);
		setChangedAndNotifyObservers();
	}
	
	public void setPenDown(int ID){
		turtleContainer.get(ID).setPenDown();
		//setChangedAndNotifyObservers();
	}
	
	public void setPenUp(int ID){
		turtleContainer.get(ID).setPenUp();
		//setChangedAndNotifyObservers();
	}
	
	public void setVisible(int ID){
		turtleContainer.get(ID).setVisible(true);
	}
	
	public void setInVisible(int ID){
		turtleContainer.get(ID).setVisible(false);
	}
	
	public boolean isVisible(int ID){
		return turtleContainer.get(ID).isVisible();
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		setChangedAndNotifyObservers();
	}
	
	public void changeTurtleImage(Integer ID, File newImageFile) {
		Image newTurtleImage = new Image(
				getClass().getClassLoader().getResourceAsStream(IMAGE_DIRECTORY + newImageFile.getName()));
		turtleContainer.get(ID).changeImage(newTurtleImage);
		setChangedAndNotifyObservers();
	}
	
	public void sendTurtleHome(int ID){
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
	
	public void setClear(boolean clear){
		this.clear = clear;
	}
	
	public boolean isClear(){
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
	
	public boolean isPenDown(int ID){
		return turtleContainer.get(ID).isPenDown();
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Map<Integer, Turtle> getTurtleContainer() {
		return turtleContainer;
	}
	
	public Point2D getHome(){
		return this.home;
	}

	private Image getDefaultTurtleImage() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}
	
	@Override
	public String toString(){
		return ("X: " + (this.getX(0) - home.getX()) + " Y: " + -1 * (this.getY(0) - home.getY()) + " Angle: " + this.getAngle(0)); 
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
	
	public Collection<CustomVariable> getUserDefinedVariables(){
		return mGlobalVariableLibrary.values();
	}

	public Collection<String> getUserDefinedCommands() {
		// TODO Auto-generated method stub
		return null;
	}

}