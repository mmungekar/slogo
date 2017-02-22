package front_end;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import back_end.ModelState;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Canvas {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String BALL_IMAGE = "ball.gif";
	public static final String BALL_IMAGE_FIRE = "ball_fire.gif";
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
		initializeTurtles();
		for (Integer ID : myTurtles.keySet()){
			root.getChildren().add(myTurtles.get(ID));
		}
	}

	private void initializeTurtles() {
		String imageLocation = IMAGE_DIRECTORY + DEFAULT_TURTLE;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		myTurtles.put(0, new Turtle(imageTurtle, home));
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
			moveTurtle(myTurtles.get(0), state);
		}
	}

	private void moveTurtle(Turtle turtle, ModelState state) {
		turtle.setX(state.getX() + home.getX());
		turtle.setY(state.getY() + home.getY());
		//turtle.setAngle(state.getAngle());
		
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
}