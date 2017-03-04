package back_end.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import back_end.commands.custom.CustomVariable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TurtleMaster {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "turtle.gif";
	
	private Point2D home;
	private Map<Integer, Turtle> turtleContainer;
	private List<Integer> activeTurtleIDs;
	private List<Integer> tempActiveTurtleIDs;
	private boolean tempActiveTurtles;
	private Integer activeTurtleID;
	
	public TurtleMaster(){
		turtleContainer = new HashMap<Integer, Turtle>();
		activeTurtleIDs = new ArrayList<Integer>();
		tempActiveTurtleIDs = new ArrayList<Integer>();
	}
	
	void setHome(Point2D home) {
		this.home = home;
		
	}
	
	private void cycleThroughActive(List<Integer> turtleIDs, Consumer<? super Turtle> action){
		turtleIDs.stream()
		        .filter(elt -> elt != null)
		        .forEach(id -> {
		        	activeTurtleID = id;
		        	Turtle turtle = turtleContainer.get(id);
		        	action.accept(turtle);
		        });
		       
	}
	
	private List<Integer> pickTurtles() {
		if (tempActiveTurtles){
			return Collections.unmodifiableList(tempActiveTurtleIDs);
		} else {
			return Collections.unmodifiableList(activeTurtleIDs);
		}
	}
	
	void moveForward(double mag) {
		moveForward(pickTurtles(), mag);
	}
	
	
	private void moveForward(List<Integer> turtleIDs, double mag) {
		cycleThroughActive(turtleIDs, );
	}
	
	void rotate(double angle) {
		rotate(pickTurtles(), angle);
	}

	private void rotate(List<Integer> turtleIDs, double angle) {
		cycleThroughActive(turtleIDs, turtle -> {
			turtle.setAngle(turtle.getAngle() + angle);
		});
	}
	
	void setVisible(boolean b){
		setVisible(pickTurtles(), b);
	}
	
	private void setVisible(List<Integer> turtleIDs, boolean b){
		cycleThroughActive(turtleIDs, turtle -> turtle.setVisible(b));
	}
	
	double sendHome(){
		double value = lastTurtleDistanceFromPos(pickTurtles(), home);
		sendHome(pickTurtles());
		return value;
	}
	
	private double lastTurtleDistanceFromPos(List<Integer> turtleIDs, Point2D pos) {
		return turtleContainer.get(turtleIDs.get(turtleIDs.size() - 1)).calcDistanceFromPos(pos);
	}

	private void sendHome(List<Integer> turtleIDs){
		cycleThroughActive(turtleIDs, turtle -> {turtle.setPosition(home); turtle.dontDrawLine();});		
	}
	
	double setPos(double inX, double inY){
		double distance = lastTurtleDistanceFromPos(pickTurtles(), new Point2D(inX, inY));
		setPos(pickTurtles(), inX, inY);
		return distance;
	}
	
	private void setPos(List<Integer> turtleIDs, double inX, double inY){
		cycleThroughActive(turtleIDs, turtle -> turtle.setPosition(inX, inY));		
	}
	
	
	boolean isVisible(){
		return isVisible(pickTurtles());
	}
	
	private boolean isVisible(List<Integer> pickTurtles) {
		return turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).isVisible();
	}
	
	void setPenDown(){
		setPenDown(pickTurtles());
	}
	
	private void setPenDown(List<Integer> turtleIDs){
		cycleThroughActive(turtleIDs, turtle -> turtle.setPenDown());
	}
	
	void setPenUp(){
		setPenUp(pickTurtles());
	}
	
	private void setPenUp(List<Integer> turtleIDs){
		cycleThroughActive(turtleIDs, turtle -> turtle.setPenUp());
	}
	
	void setAngle(double angle) {
		setAngle(pickTurtles(), angle);
	}

	private void setAngle(List<Integer> turtleIDs, double angle) {
		cycleThroughActive(turtleIDs, turtle -> {
			turtle.setAngle(angle);
		});
	}
	
	boolean isPenDown(){
		return isPenDown(pickTurtles());
	}
	
	private boolean isPenDown(List<Integer> pickTurtles) {
		return turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).isPenDown();
	}
	
	double setTowards(double ox, double oy) {
		double angleChange = lastTurtleAngleChange(pickTurtles(), ox, oy);
		setTowards(pickTurtles(), ox, oy);
		return angleChange;
	}

	private double lastTurtleAngleChange(List<Integer> turtleIDs, double ox, double oy) {
		return operationOnLastTurtle(ox, oy)
				
				setTowards(turtleIDs.size() - 1, ox, oy);
	}

	private void setTowards(List<Integer> turtleIDs, double ox, double oy) {
		cycleThroughActive(turtleIDs, turtle -> {
			turtle.setTowards(home.getX() + ox, home.getY() - oy);
		});
	}
	
	double clearScreen() {
		// TODO remove lines
		List<Integer> allTurtleIDs = new ArrayList<Integer>(turtleContainer.keySet());
		double value = lastTurtleDistanceFromPos(allTurtleIDs, home);
		sendHome(allTurtleIDs);
		return value;
	}
	
	public List<Integer> getAllTurtleIDs() {
		return Collections.unmodifiableList(new ArrayList<Integer>(turtleContainer.keySet()));
	}

	double getCoordinate(Integer coordinate) {
		return getCoordinate(pickTurtles(), coordinate);
	}
	
	private double getCoordinate(List<Integer> pickTurtles, Integer coordinate){
		Point2D lastTurtleCenterPos = turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).getCenterPosition();
		
		double[] pos = new double[]{lastTurtleCenterPos.getX(), lastTurtleCenterPos.getY()};
		double[] homePos = new double[]{home.getX(), home.getY()};
		return homePos[coordinate] - (coordinate.equals(0)? 0 : -1) * pos[coordinate];
		
	}

	Collection<Turtle> getTurtles() {
		return turtleContainer.values();
	}

	void breedTurtle(int newTurtleID) {
		if(newTurtleID == -1){
			newTurtleID = findLowestIDnotTaken();
		}
		turtleContainer.put(newTurtleID, new Turtle(getDefaultTurtleImage(), home));
		
	}

	private int findLowestIDnotTaken() {
		int newID = 1;
		while(turtleContainer.keySet().contains(newID)){
			newID += 1;
		}
		return newID;
	}
	
	private Image getDefaultTurtleImage() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}
	
	void changeTurtleImage(File newImageFile) {
		Image newTurtleImage = new Image(
				getClass().getClassLoader().getResourceAsStream(IMAGE_DIRECTORY + newImageFile.getName()));
		changeTurtleImage(pickTurtles(), newTurtleImage);
	}

	private void changeTurtleImage(List<Integer> turtleIDs, Image newTurtleImage) {
		cycleThroughActive(turtleIDs, turtle -> {
			turtle.changeImage(newTurtleImage);
		});
	}

	public void setActiveTurtles(List<Integer> newActives) {
		this.activeTurtleIDs = newActives;
	}	
	
	public void setTempActiveTurtles(List<Integer> newTempActives) {
		this.tempActiveTurtleIDs = newTempActives;
		this.tempActiveTurtles = true;
	}
}
