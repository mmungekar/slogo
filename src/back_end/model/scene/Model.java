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
import back_end.exceptions.NotInMapException;
import back_end.exceptions.VariableNotFoundException;
import back_end.commands.custom.CustomVariable;
import back_end.commands.custom.CustomCommand;
import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.VariableLibrary;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Model extends Observable {
	public static final String DEFAULT_LANGUAGE = "English";

	private String currentLanguage = DEFAULT_LANGUAGE;

	private Point2D home;
	private boolean clear;

	private TurtleMaster myTurtleMaster;
	private Artist myArtist;
	private CustomMaster myCustomMaster;
	private Animator myAnimator;

	public Model() {
		myTurtleMaster = new TurtleMaster(this);
		myArtist = new Artist(this);
		myCustomMaster = new CustomMaster(this);
	}

	
	public TurtleMaster getTurtleMaster() {
		return myTurtleMaster;
	}

	public Artist getDrawer() {
		return myArtist;
	}

	public CustomMaster getCustomMaster() {
		return myCustomMaster;
	}

	public void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
		setChangedAndNotifyObservers();
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setHome(Point2D home) {
		this.home = home;
		myTurtleMaster.setHome(home);
		myTurtleMaster.breedTurtle(0);
		myTurtleMaster.setActiveTurtles(Arrays.asList(0));
		setChangedAndNotifyObservers();
	}

	public Point2D getHome() {
		return this.home;
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}

	public void sendError(Exception e) {
		// TODO Auto-generated method stub

	}

}