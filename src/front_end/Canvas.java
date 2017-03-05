package front_end;

import java.util.Observable;
import java.util.Observer;

import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Canvas extends Rectangle implements Observer {
	public static final int arc = 25;

	private Group myRoot;
	private Model observedModel = null;

	public Canvas(Model model, Group root) {
		this.observedModel = model;
		this.myRoot = root;
		formatBorder();
		model.addObserver(this);
	}

	private void formatBorder() {
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(10);
		this.setArcHeight(arc);
		this.setArcWidth(arc);
	}

	@Override
	public void update(Observable obs, Object obj) {
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
		this.setFill(observedModel.getBackgroundColor());
	}

	private void drawLine(Turtle turtle, Point2D startPos, Point2D endPos) {
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