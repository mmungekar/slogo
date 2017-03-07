package back_end.model.scene;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import back_end.commands.custom.CustomCommand;
import back_end.commands.custom.CustomVariable;
import back_end.exceptions.VariableNotFoundException;

import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.VariableLibrary;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Model extends Observable {
	public static final String DEFAULT_LANGUAGE = "English";
	public static final int DEFAULT_BACKGROUND_COLOR_INDEX = 0;
	public static final List<String> DEFAULT_COLOR_HTML_NAMES = Arrays.asList("white", "black", "blue", "red", "green",
			"yellow", "orange");
	
	private String currentLanguage = DEFAULT_LANGUAGE;
	private Color backgroundColor;
	private Point2D home;
	private boolean clear;
	public VariableLibrary mGlobalVariableLibrary;
	public VariableLibrary mLocalVariableLibrary;
	public CustomCommandLibrary mCustomCommandLibrary;
	private HashMap<Integer, Color> colorContainer;
	private TurtleMaster myTurtleMaster;

	public Model() {
		myTurtleMaster = new TurtleMaster();
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


	public void setClear(boolean clear) {
		this.clear = clear;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Point2D getHome() {
		return this.home;
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
		setChangedAndNotifyObservers();

	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		setChangedAndNotifyObservers();
	}


	public void setBackgroundColor(int index) {
		setBackgroundColor(colorContainer.get(index));
	}
	
	private void createDefaultColors() {
		colorContainer = new HashMap<Integer, Color>();
		for (int i = 0; i < DEFAULT_COLOR_HTML_NAMES.size(); i++) {
			colorContainer.put(i, Color.web(DEFAULT_COLOR_HTML_NAMES.get(i)));
		}
		backgroundColor = colorContainer.get(DEFAULT_BACKGROUND_COLOR_INDEX);
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
	
	public void setHome(Point2D home) {
		this.home = home;
		myTurtleMaster.setHome(home);
		myTurtleMaster.breedTurtle(0);
		myTurtleMaster.setActiveTurtles(Arrays.asList(0));
		setChangedAndNotifyObservers();
	}
	
	public Iterator<Turtle> getTurtleIterator(){
		return myTurtleMaster.getTurtles().iterator();
	}

	public double clearScreen() {
		double a = myTurtleMaster.clearScreen();
		setChangedAndNotifyObservers();
		return a;
	}


	public void setInVisible() {
		myTurtleMaster.setVisible(false);
		setChangedAndNotifyObservers();

	}

	public double sendTurtleHome() {
		double a = myTurtleMaster.sendHome();
		setChangedAndNotifyObservers();
		return a;
	}

	public boolean isPenDown() {
		return myTurtleMaster.isPenDown();
	}

	public void moveForward(double mag) {
		myTurtleMaster.moveForward(mag);
		setChangedAndNotifyObservers();
	}

	public void rotate(double angle) {
		myTurtleMaster.rotate(angle);
		setChangedAndNotifyObservers();		
	}

	public boolean isVisible() {
		return myTurtleMaster.isVisible();
	}

	public void setPenDown() {
		myTurtleMaster.setPenDown();
	}

	public void setPenUp() {
		myTurtleMaster.setPenUp();
	}

	public void setAngle(double a) {
		myTurtleMaster.setAngle(a);
		setChangedAndNotifyObservers();
	}


	public double setPos(double d, double e) {
		double a = myTurtleMaster.setPos(d, e);
		setChangedAndNotifyObservers();
		return a;
	}

	public double setTowards(double ox, double oy) {
		double a = myTurtleMaster.setTowards(ox, oy);
		setChangedAndNotifyObservers();
		return a;
	}

	public void setVisible() {
		myTurtleMaster.setVisible(true);
		setChangedAndNotifyObservers();
	}


	public double getTurtleCount() {
		return myTurtleMaster.getAllTurtleIDs().size();
	}

	public double getCoordinate(Integer coordinate) {
		return myTurtleMaster.getCoordinate(coordinate);
	}

	public void createTurtle(int newID) {
		myTurtleMaster.breedTurtle(newID);
		setChangedAndNotifyObservers();
	}
	
	public double getActiveTurtleID() {
		return myTurtleMaster.getActiveTurtleID();
	}

	public void tell(List<Integer> parametersInteger) {
		myTurtleMaster.setActiveTurtles(parametersInteger);
		setChangedAndNotifyObservers();
	}
}
