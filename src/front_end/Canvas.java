package front_end;

import java.util.Observable;
import java.util.Observer;

import back_end.model.Model;
import back_end.model.Turtle;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Canvas implements Observer {
	public static final int arc = 25;

	private Model observedModel = null;
	private Rectangle rectangle;
	private Group myRoot;

	public Canvas(Model model)
	{
		myRoot = new Group();
		this.observedModel = model;
		addRectangle();
		model.addObserver(this);
	}

	private void addRectangle() {
		rectangle = new Rectangle();
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(10);
		rectangle.setArcHeight(arc);
		rectangle.setArcWidth(arc);
		updateBackground();
		myRoot.getChildren().add(rectangle);
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

	private void updateBackground()
	{
		rectangle.setFill(observedModel.getBackgroundColor());
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

	public void setWidth(int canvasWidth)
	{
		rectangle.setWidth(canvasWidth);
	}

	public void setHeight(int canvasHeight)
	{
		rectangle.setHeight(canvasHeight);
	}

	public Node getRoot()
	{
		return myRoot;
	}
}