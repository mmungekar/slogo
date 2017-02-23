package front_end;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import back_end.ModelState;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Canvas {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "ball_given.gif";
	
	public static final int arc = 25;
	
	private Rectangle Frame;
	private Map<Integer, Turtle> myTurtles = new HashMap<Integer, Turtle>();
	private Point2D home;
	private ModelState oldState;
	private Group myRoot;
	
	public Canvas(Group root, Point2D home) {
		this.home = home;
		this.myRoot = root;
		createRectangle();
		root.getChildren().add(Frame);
		createFirstTurtle();
	}

	private void createFirstTurtle() {
		myTurtles.put(0, new Turtle(getDefaultTurtleImage(), home));
		addNewTurtle(myTurtles.get(0));
	}

	private void addNewTurtle(Turtle turtle) {
		myRoot.getChildren().add(turtle);
		
	}

	private Image getDefaultTurtleImage() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}

	private void createRectangle() {
		Frame = new Rectangle();
		Frame.setStroke(Color.BLACK);
		Frame.setStrokeWidth(10);
		Frame.setArcHeight(arc);
		Frame.setArcWidth(arc);
	}

	public void setBackgroundColor(Color input) {
		Frame.setFill(input);
	}

	public void setPosition(int[] pos) {
		Frame.setX(pos[0]);
		Frame.setY(pos[1]);
	}

	public void setSize(int[] size) {
		Frame.setWidth(size[0]);
		Frame.setHeight(size[1]);
	}

	void update(ModelState state) {		
		
		if (oldState == null || !oldState.equals(state)){
			oldState = state.copy();
			//myTurtles.get(0).setPenDown(false);
			moveTurtle(myTurtles.get(0), state);
		}
	}

	private void moveTurtle(Turtle turtle, ModelState state) {
		// TODO Turtle pen
		// TODO make it a motion and not an instant jump
		if(turtle.isPenDown()){
			drawLine(turtle.getCenterX(), turtle.getCenterY(), state.getX() + home.getX(), state.getY() + home.getY());
		}
		turtle.setCenterX(state.getX() + home.getX());
		turtle.setCenterY(state.getY() + home.getY());
		
		//turtle.setAngle(state.getAngle());
		
	}

	private void drawLine(double startX, double startY, double endX, double endY) {
		Line line = new Line();
		line.setStartX(startX);
		line.setStartY(startY);
		line.setEndX(endX);
		line.setEndY(endY);
		line.setStroke(Color.BLACK);
		myRoot.getChildren().add(line);
		
	}

	void changeTurtleImage(int ID, File newImageFile) {
		Image newTurtleImage = new Image(getClass().getClassLoader().getResourceAsStream(IMAGE_DIRECTORY + newImageFile.getName()));
		myRoot.getChildren().remove(myTurtles.get(ID));
		myTurtles.put(ID, new Turtle(newTurtleImage, myTurtles.get(ID)));
		myRoot.getChildren().add(myTurtles.get(ID));
	}

	Collection<Integer> getTurtleIDs() {
		return myTurtles.keySet();
	}

	void createTurtle() {
		int newTurtleID = Collections.max(myTurtles.keySet()) + 1;
		myTurtles.put(newTurtleID, new Turtle(getDefaultTurtleImage(), home));
		addNewTurtle(myTurtles.get(newTurtleID));
	}

	
}