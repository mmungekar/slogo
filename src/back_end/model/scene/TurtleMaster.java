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


public class TurtleMaster {
	private Point2D home;
	private Map<Integer, Turtle> turtleContainer;
	private List<Integer> activeTurtleIDs;
	private List<Integer> tempActiveTurtleIDs;
	private boolean tempActiveTurtles;
	private Integer activeTurtleID;

	public TurtleMaster() {
		turtleContainer = new HashMap<Integer, Turtle>();
		activeTurtleIDs = new ArrayList<Integer>();
		tempActiveTurtleIDs = new ArrayList<Integer>();
		tempActiveTurtles = false;
		activeTurtleID = 0;
	}

	public void setHome(Point2D home) {
		this.home = home;
	}
	
	public double operateOnTurtle(Function<Turtle, Double> action) {
		return cycleThroughActive(getListeningTurtleIDs(), action);
	}

	private double cycleThroughActive(List<Integer> turtleIDs, Function<Turtle, Double> action) {
		List<Double> results = new ArrayList<Double>();
		turtleIDs.stream().filter(elt -> elt != null).forEach(id -> {
			activeTurtleID = id;
			Turtle turtle = turtleContainer.get(id);
			results.add(action.apply(turtle));
		});
		return results.get(results.size() - 1);
	}
	
	private List<Integer> getListeningTurtleIDs() {
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

	double getCoordinate(Integer coordinate) {
		return getCoordinate(getListeningTurtleIDs(), coordinate);
	}

	private double getCoordinate(List<Integer> pickTurtles, Integer coordinate) {
		Point2D lastTurtleCenterPos = turtleContainer.get(pickTurtles.get(pickTurtles.size() - 1)).getCenterPosition();
		double[] pos = new double[] { lastTurtleCenterPos.getX(), lastTurtleCenterPos.getY() };
		double[] homePos = new double[] { home.getX(), home.getY() };
		//return homePos[coordinate] + (coordinate.equals(0) ? 1 : -1) * pos[coordinate];
		return (coordinate.equals(0) ? -1 : 1) * (homePos[coordinate] - pos[coordinate]);
	}

	public void breedTurtle(int newTurtleID) {
		if (newTurtleID == -1) {
			newTurtleID = findLowestIDnotTaken();
		}
		turtleContainer.put(newTurtleID, new Turtle(home));

	}

	private int findLowestIDnotTaken() {
		int newID = 1;
		while (turtleContainer.keySet().contains(newID)) {
			newID += 1;
		}
		return newID;
	}

	
	public void setActiveTurtles(List<Integer> newActives) {

		newActives.stream().forEach(id -> {
			if (!turtleContainer.containsKey(id)) {
				this.breedTurtle(id);
			}
		});

		this.activeTurtleIDs = newActives;
	}

	public void setTempActiveTurtles(List<Integer> newTempActives) {
		this.tempActiveTurtleIDs = newTempActives;
		this.tempActiveTurtles = true;
	}

	public double getActiveTurtleID() {
		return this.activeTurtleID;
	}

	public void removeActiveTurtles() {
		activeTurtleIDs.stream().filter(elt -> elt != null).forEach(id -> { 
			turtleContainer.remove(id);
		});
	}

	public Iterator<Turtle> getTurtleIterator() {
		return turtleContainer.values().iterator();
	}

	
}
