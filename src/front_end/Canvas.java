package front_end;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import back_end.model.scene.Animator;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
/**
 * By Miguel Anderson, Juan Philippe
 *
 */

public class Canvas implements Observer {
	public static final int arc = 25;

	private Model observedModel = null;
	private Animator observedAnimator = null;
	private Rectangle rectangle;
	private Group myRoot;
	private List<ImageView> myStamps;

	public Canvas(Model model)
	{
		myRoot = new Group();
		this.observedModel = model;
		addRectangle();
		model.addObserver(this);
		myStamps = new ArrayList<ImageView>();
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
		if (obs == observedModel || obs == observedAnimator) {
			// update all parts of modelstate that canvas has
			Iterator<Turtle> turtleIterator = observedModel.getTurtleMaster().getTurtleIterator();
			List<ImageView> stampList = observedModel.getTurtleMaster().getStamps();
			while(turtleIterator.hasNext()) {
				Turtle turtle = turtleIterator.next();
				if(turtle.hasMoved() && turtle.isPenDown()){
					drawLine(turtle, turtle.getPrevCenterPosition(), turtle.getCenterPosition());
					turtle.dontDrawLine();
				}
				if (!myRoot.getChildren().contains(turtle.getImageView())) {
					myRoot.getChildren().add(turtle.getImageView());
					observedAnimator = observedModel.getTurtleMaster().getAnimator();
					observedModel.getTurtleMaster().getAnimator().addObserver(this);
				}
			}
			
			manageStamps(stampList);
			updateBackground();
		}

	}

	private void manageStamps(List<ImageView> stampList) {
		if(stampList.size()!=0){
		for(ImageView stamp:stampList){
			if(!myRoot.getChildren().contains(stamp)){
				myRoot.getChildren().add(stamp);
				myStamps.add(stamp);
			}
			
		}
}
		else{
			for(ImageView stamp:myStamps){
				myRoot.getChildren().remove(stamp);
			}
		}
	}

	private void updateBackground()
	{
		rectangle.setFill(observedModel.getDrawer().getBackgroundColor());
	}

	private void drawLine(Turtle turtle, Point2D startPos, Point2D endPos) {
		//System.out.println("Making Line");
		Line line = new Line();
		line.setStartX(startPos.getX());
		line.setStartY(startPos.getY());
		line.setEndX(endPos.getX());
		line.setEndY(endPos.getY());
		line.setStroke(turtle.getPenColor());
		line.setStrokeWidth(turtle.getPenSize());
		myRoot.getChildren().add(line);
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