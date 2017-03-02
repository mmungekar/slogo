package back_end.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Turtle extends ImageView {
	private Point2D topLeftPos;
	private Point2D centerPos = null;
	private Point2D prevCenterPos = null;
	private boolean penDown;
	private double[] halfDems = new double[2];
	private double angle;
	private Color penColor = Color.BLACK;

	Turtle(Image image, Point2D initPos) {
		super(image);
		this.setAngle(0);
		calcHalfDems();
		setAngle(-90);
		setPosition(initPos);
		penDown = true;
	}

	private void calcHalfDems() {
		halfDems[0] = this.getImage().getWidth() / 2.0;
		halfDems[1] = this.getImage().getHeight() / 2.0;
	}

	Turtle(Image image, double x, double y) {
		this(image, new Point2D(x, y));
	}

	public void setPosition(double inX, double inY) {
		setPosition(new Point2D(inX, inY));
	}

	public void setPosition(Point2D newPos) {
		if (this.centerPos != null) {
			this.prevCenterPos = this.centerPos;
		} else {
			this.prevCenterPos = newPos;
		}
		this.centerPos = newPos;
		this.setX(this.centerPos.getX() - halfDems[0]);
		this.setY(this.centerPos.getY() - halfDems[1]);
		this.topLeftPos = new Point2D(this.getX(), this.getY());
	}

	public boolean isPenDown() {
		return penDown;
	}

	public void setPenDown() {
		this.penDown = true;
	}
	
	public void setPenUp() {
		this.penDown = false;
	}

	public Point2D getCenterPosition() {
		return this.centerPos;
	}

	public Point2D getTopLeftPosition() {
		return this.topLeftPos;
	}
	
	public Point2D getPrevCenterPosition() {
		return this.prevCenterPos;
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		keepAngleWithin360();
		this.setRotate(this.angle);
	}

	private void keepAngleWithin360() {
		if (Math.abs(this.angle) >= 360){
			this.angle -= Math.signum(this.angle) * 360;
		}
	}

	public void changeImage(Image newTurtleImage) {
		this.setImage(newTurtleImage);
		calcHalfDems();
		setPosition(this.centerPos);
	}

	public boolean hasMoved() {
		return (!this.prevCenterPos.equals(this.centerPos));
	}

	public void dontDrawLine() {
		this.prevCenterPos = this.centerPos;
	}
	
	public Color getPenColor(){
		return this.penColor;
	}

	public void changePenColor(Color newColor) {
		this.penColor = newColor;
		
	}

}
