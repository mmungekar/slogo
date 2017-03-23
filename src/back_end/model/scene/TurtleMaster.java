package back_end.model.scene;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javafx.geometry.Point2D;
/**
 * By Miguel Anderson
 * Modified by Mina Mungekar
 */
public class TurtleMaster {
	private Point2D home;
	private Map<Integer, Turtle> turtleContainer;
	private List<Integer> activeTurtleIDs;
	private List<Integer> tempActiveTurtleIDs;
	private boolean tempActiveTurtles;
	private Integer activeTurtleID;
	private Animator myAnimator;
	private Turtle currTurtle;
	private Model myModel;

	public TurtleMaster(Model model) {
		this.myModel = model;
		turtleContainer = new HashMap<Integer, Turtle>();
		activeTurtleIDs = new ArrayList<Integer>();
		tempActiveTurtleIDs = new ArrayList<Integer>();
		tempActiveTurtles = false;
		activeTurtleID = 0;
		myAnimator = new Animator(this);
	}
	
	
	public Animator getAnimator(){
		return myAnimator;
	}
	
	
	public Turtle getTurtle(Integer id){
		return turtleContainer.get(id);
	}
	
	public void setActiveTurtleID(int ID){
		activeTurtleID = ID ;
	}

	private void notifyModel() {
		myModel.setChangedAndNotifyObservers();
	}

	public void setHome(Point2D home) {
		this.home = home;
	}

	public double operateOnTurtle(Function<Turtle, Double> action) {
		return cycleThroughActive(getListeningTurtleIDs(), action);
	}

	private double cycleThroughActive(List<Integer> turtleIDs, Function<Turtle, Double> action) {
		if(myAnimator.isRunning()){
			myAnimator.getQueue().add(action);
			List<Double> results = new ArrayList<Double>();
			results.add(action.apply(currTurtle));
			notifyModel();
			return results.get(results.size() - 1);
		}
		
		else{
		List<Double> results = new ArrayList<Double>();
		turtleIDs.stream().filter(elt -> elt != null).forEach(id -> {
			activeTurtleID = id;
			Turtle turtle = turtleContainer.get(id);
			results.add(action.apply(turtle));
		});
		notifyModel();
		return results.get(results.size() - 1);
		}
	}

	public List<Integer> getListeningTurtleIDs() {
		if (tempActiveTurtles) {
			return Collections.unmodifiableList(tempActiveTurtleIDs);
		} else {
			return Collections.unmodifiableList(activeTurtleIDs);
		}
	}

	public void setTurtleImage(File newImageFile) {
		cycleThroughActive(getListeningTurtleIDs(), turtle -> turtle.changeImage(newImageFile.getName()));
	}

	public List<Integer> getAllTurtleIDs() {
		return Collections.unmodifiableList(new ArrayList<Integer>(turtleContainer.keySet()));
	}

	public double getCoordinate(Integer coordinate) {
		return getCoordinate(getListeningTurtleIDs(), coordinate);
	}

	private double getCoordinate(List<Integer> pickTurtles, Integer coordinate) {
		Point2D lastTurtleCenterPos = turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).getCenterPosition();
		double[] pos = new double[] { lastTurtleCenterPos.getX(), lastTurtleCenterPos.getY() };
		double[] homePos = new double[] { home.getX(), home.getY() };
		// return homePos[coordinate] + (coordinate.equals(0) ? 1 : -1) *
		// pos[coordinate];
		return (coordinate.equals(0) ? -1 : 1) * (homePos[coordinate] - pos[coordinate]);
	}

	public void breedTurtle(int newTurtleID) {
		if (newTurtleID == -1) {
			newTurtleID = findLowestIDnotTaken();
		}
		turtleContainer.put(newTurtleID, new Turtle(home));
		Turtle t = new Turtle(home);
		currTurtle = new Turtle(home);
		t.addObserver(myAnimator);
		turtleContainer.put(newTurtleID, t);
		notifyModel();
	}

	private int findLowestIDnotTaken() {
		int newID = 1;
		while (turtleContainer.keySet().contains(newID)) {
			newID += 1;
		}
		return newID;
	}

	public void setActiveTurtles(List<Integer> newActives) {
		addNeededTurtles(newActives);
		this.activeTurtleIDs = newActives;
		notifyModel();
	}


	private void addNeededTurtles(List<Integer> newActives) {
		newActives.stream().forEach(id -> {
			if (!turtleContainer.containsKey(id)) {
				this.breedTurtle(id);
			}
		});
	}

	public void setTempActiveTurtles(List<Integer> newTempActives) {
		addNeededTurtles(newTempActives);
		this.tempActiveTurtles = true;
		this.tempActiveTurtleIDs = newTempActives;
		notifyModel();
	}
	
	public List getTurtles(){
		return tempActiveTurtleIDs;
	}

	public void revertActiveTurtles() {
		this.tempActiveTurtles = false;
	}

	public double getActiveTurtleID() {
		return this.activeTurtleID;
	}

	public void removeActiveTurtles() {
		activeTurtleIDs.stream().filter(elt -> elt != null).forEach(id -> {
			turtleContainer.remove(id);
		});
		notifyModel();
	}

	public Iterator<Turtle> getTurtleIterator() {
		return turtleContainer.values().iterator();
	}


	public void printStatus(int index) {
		myModel.sendToOutput(turtleContainer.get(index).printStatus(home));
	}
	
	public List<Point2D> getCenterPositions() {
		List<Point2D> centerPositions = new ArrayList<Point2D>();
		this.getAllTurtleIDs().stream().forEach(id -> {
    		centerPositions.add(this.turtleContainer.get(id).getCenterPosition());
    	});
		return centerPositions;
	}

}