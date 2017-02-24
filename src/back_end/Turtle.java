package back_end;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends ImageView{
	private Point2D pos;
	private boolean penDown;
	private double[] halfDems = new double[2];
	private double angle;
	
	Turtle(Image image, Point2D initPos){
		super(image);
		initializeSize(image);
		updatePosition(initPos);
		penDown = true;
	}
	
	private void initializeSize(Image image) {
		halfDems[0] = image.getWidth() / 2.0;
		halfDems[1] = image.getHeight() / 2.0;
		
	}

	Turtle(Image image, double x, double y){
		this(image, new Point2D(x, y));
	}
	
	private void updatePosition(double x, double y) {
		updatePosition(new Point2D(x, y));	
	}

	private void updatePosition(Point2D newPos) {
		this.pos = newPos;
		this.setCenterX(pos.getX());
		this.setCenterY(pos.getY());
	}
	
	public boolean isPenDown() {
		return penDown;
	}

	public void setPenDown(boolean penDown) {
		this.penDown = penDown;
	}
	
	public void setCenterX(double x){
		this.setX(x - halfDems[0]);
	}
	
	public void setCenterY(double y){
		this.setY(y - halfDems[1]);
	}
	
	public double getCenterX(){
		return this.getX() + halfDems[0];
	}
	
	public double getCenterY(){
		return this.getY() + halfDems[1];
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		this.setRotate(this.angle);
		//TODO decide on angle rotation
	}

}
