package back_end.model.scene;

import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.function.Consumer;
import java.util.function.Function;

import back_end.commands.custom.CustomCommand;
import back_end.commands.custom.CustomVariable;
import back_end.exceptions.VariableNotFoundException;

import back_end.commands.custom.CustomVariable;
import back_end.commands.custom.CustomCommand;
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
	private VariableLibrary mGlobalVariableLibrary;
	private VariableLibrary mLocalVariableLibrary;
	private CustomCommandLibrary mCustomCommandLibrary;
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
			vars.add(mGlobalVariableLibrary.get(key));
			
		}
		return vars;
	}

	public Collection<String> getUserDefinedCommands() {
		return mCustomCommandLibrary.keySet();
	}

	public boolean hasCustomVariable(String parameter) {
		return mLocalVariableLibrary.hasVariable(parameter);
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

	

	public void removeActiveTurtles() {
		myTurtleMaster.removeActiveTurtles();
	}

	public void clearVariables() {
		mGlobalVariableLibrary = new VariableLibrary();
		mLocalVariableLibrary = new VariableLibrary();
	}

	public void clearCommands() {
		mCustomCommandLibrary = new CustomCommandLibrary();
	}

	public boolean isVariableStored(String parameter) {
		return this.mGlobalVariableLibrary.hasVariable(parameter)
				|| this.mLocalVariableLibrary.hasVariable(parameter);
	}

	public VariableLibrary getLocalVariableLibrary() {
		return this.mLocalVariableLibrary;
	}

	public void setLocalVariableLibrary(VariableLibrary mCustomVarLib) {
		this.mLocalVariableLibrary = mCustomVarLib;
	}

	public Double retrieveVariable(String nodeName) {
		if(this.mGlobalVariableLibrary.hasVariable(nodeName)){
			return this.mGlobalVariableLibrary.get(nodeName).getValue();
		} else if (this.mLocalVariableLibrary.hasVariable(nodeName)){
			return this.mLocalVariableLibrary.get(nodeName).getValue();
		} return 0d;
	}

	public double operateOnTurtle(Function<Turtle, Double> action) {
		double result = myTurtleMaster.operateOnTurtle(action);
		setChangedAndNotifyObservers();
		return result;
	}
}
