package front_end;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import back_end.Model;
import back_end.Turtle;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Canvas implements Observer {
	public static final int arc = 25;

	private Rectangle Frame;
	private Group myRoot;
	private Model observedModel = null;
	private HashMap<Integer, Turtle> turtleContainer = new HashMap<>();

	public Canvas(Model model, Group root, Point2D home) {
		this.observedModel = model;
		this.myRoot = root;
		createRectangle();
		root.getChildren().add(Frame);
		model.addObserver(this);
		model.setHome(home);
		//this.turtleContainer = observedState.getTurtleContainer();
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

	@Override
	public void update(Observable obs, Object obj) {
		// System.out.println("(from Canvas/Observer end) Observers Notified ");
		if (obs == observedModel) {
			// update all parts of modelstate that canvas has
			for (Turtle turtle : observedModel.getTurtleContainer().values()){
				if(turtle.hasMoved() && turtle.isPenDown()){
					drawLine(turtle, turtle.getPrevCenterPosition(), turtle.getCenterPosition());
					turtle.dontDrawLine();
				}
			}
			addNewTurtles();
			updateBackground();
		}

	}

	private void updateBackground() {
		this.setBackgroundColor(observedModel.getBackgroundColor());
	}

	public void drawLine(Turtle turtle, Point2D startPos, Point2D endPos) {
		Line line = new Line();
		line.setStartX(startPos.getX());
		line.setStartY(startPos.getY());
		line.setEndX(endPos.getX());
		line.setEndY(endPos.getY());
		line.setStroke(turtle.getPenColor());
		myRoot.getChildren().add(line);
	}

	private void addNewTurtles() {
		for (Turtle turtle : observedModel.getTurtleContainer().values()) {
			if (!myRoot.getChildren().contains(turtle)) {
				myRoot.getChildren().add(turtle);
			}
		}
	}
}