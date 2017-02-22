package front_end;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends ImageView{
	private Point2D pos;
	private double velo;
	
	Turtle(Image image, Point2D initPos){
		super(image);
		updatePosition(initPos);
	}
	
	Turtle(Image image, double x, double y){
		this(image, new Point2D(x, y));
	}
	
	Turtle(Image newImage, Turtle oldTurtle){
		super(newImage);
		copyOverAttributes(oldTurtle);
	}
	
	private void updatePosition(double x, double y) {
		updatePosition(new Point2D(x, y));	
	}

	private void copyOverAttributes(Turtle oldTurtle) {
		updatePosition(oldTurtle.getX(), oldTurtle.getY());
	}

	private void updatePosition(Point2D newPos) {
		this.pos = newPos;
		this.setX(pos.getX());
		this.setY(pos.getY());
	}

	public double getVelo() {
		return velo;
	}

	public void setVelo(double velo) {
		this.velo = velo;
	}

}
