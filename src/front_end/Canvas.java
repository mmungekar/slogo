package front_end;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import back_end.ModelState;
import back_end.Turtle;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Canvas implements Observer{
	
	public static final int arc = 25;
	
	private Rectangle Frame;
	private Group myRoot;
	
	private ModelState observedState = null;
	private Map<Integer, Turtle> turtleContainer = new HashMap<>();
	
	public Canvas(ModelState observedState, Group root, Point2D home) {
		this.observedState = observedState;
		this.myRoot = root;
		createRectangle();
		root.getChildren().add(Frame);
		observedState.setHome(home);
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

	private void drawLine(double startX, double startY, double endX, double endY) {
		Line line = new Line();
		line.setStartX(startX);
		line.setStartY(startY);
		line.setEndX(endX);
		line.setEndY(endY);
		line.setStroke(Color.BLACK);
		myRoot.getChildren().add(line);
		
	}

	Collection<Integer> getTurtleIDs() {
		return turtleContainer.keySet();
	}

	@Override
	public void update(Observable obs, Object obj) {
		System.out.println("(from Canvas/Observer end) Observers Notified ");
		if (obs == observedState){
			// update all parts of modelstate that canvas has
			updateTurtles();
			updateBackground();
		}
		
	}

	private void updateBackground() {
		this.setBackgroundColor(observedState.getBackgroundColor());
	}

	private void updateTurtles() {
		this.turtleContainer = observedState.getTurtleContainer();
		for (Turtle turtle : turtleContainer.values()){
			if (!myRoot.getChildren().contains(turtle)){
				System.out.println("added new turtle");
				myRoot.getChildren().add(turtle);
			}
		}
	}

	
}