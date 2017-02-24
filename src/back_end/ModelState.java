package back_end;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ModelState extends Observable {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "ball_given.gif";

	private Map<Integer, Turtle> turtleContainer = new HashMap<>();
	private Color backgroundColor;
	private Point2D home;

	public ModelState() {
		setBackgroundColor(Color.WHITE);
	}

	private void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
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
		turtleContainer.get(ID).setPenDown(true);
		setChangedAndNotifyObservers();
	}
	
	public void setPenUp(int ID){
		turtleContainer.get(ID).setPenDown(false);
		setChangedAndNotifyObservers();
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

	private Image getDefaultTurtleImage() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}
	
	@Override
	public String toString(){
		return ("X: " + (this.getX(0) - home.getX()) + " Y: " + -1 * (this.getY(0) - home.getY()) + " Angle: " + this.getAngle(0)); 
	}

}
