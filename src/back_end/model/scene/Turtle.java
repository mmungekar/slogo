package back_end.model.scene;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Turtle {
	private ImageView myImageView;
	
	private Point2D topLeftPos;
	private Point2D centerPos = null;
	private Point2D prevCenterPos = null;
	private boolean penDown;
	private double[] halfDems = new double[2];
	private double angle;
	private Color penColor = Color.BLACK;

	Turtle(Image image, Point2D initPos) {		
		myImageView = new ImageView(image);
		this.setAngle(0);
		calcHalfDems();
		setAngle(-90);
		setPosition(initPos);
		penDown = true;
	}

	private void calcHalfDems() {
		halfDems[0] = myImageView.getImage().getWidth() / 2.0;
		halfDems[1] = myImageView.getImage().getHeight() / 2.0;
	}

	Turtle(Image image, double x, double y) {
		this(image, new Point2D(x, y));
	}

	public void setPosition(double inX, double inY) {
		setPosition(new Point2D(inX, inY));
	}

	public double setPosition(Point2D newPos) {
		double displacement = 0;
		if (this.centerPos != null) {
			this.prevCenterPos = this.centerPos;
			displacement = this.centerPos.distance(newPos);
		} else {
			this.prevCenterPos = newPos;
		}
		
		this.centerPos = newPos;
		myImageView.setX(this.centerPos.getX() - halfDems[0]);
		myImageView.setY(this.centerPos.getY() - halfDems[1]);
		this.topLeftPos = new Point2D(myImageView.getX(), myImageView.getY());
		return displacement;
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
		myImageView.setRotate(this.angle);
	}

	private void keepAngleWithin360() {
		if (Math.abs(this.angle) >= 360){
			this.angle -= Math.signum(this.angle) * 360;
		}
	}

	public void changeImage(Image newTurtleImage) {
		myImageView.setImage(newTurtleImage);
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

	public boolean isVisible() {
		return myImageView.isVisible();
	}

	public double calcDistanceFromPos(Point2D pos) {
		return Math.sqrt(Math.pow((pos.getX() - this.getCenterPosition().getX()), 2) + Math.pow((pos.getY() - this.getCenterPosition().getY()), 2)); 
	}

	public void setVisible(boolean b) {
		myImageView.setVisible(b);
	}

	public Node getImageView() {
		return myImageView;
	}
	
	public void moveForward(double mag) {
		double angle = this.getAngle();
		double dx = Math.cos(Math.toRadians(angle)) * mag;
		double dy = Math.sin(Math.toRadians(angle)) * mag;
		this.setPosition(this.getCenterPosition().add(dx, dy));	
	}
	
	public double setTowards(double ox, double oy) {
		double dx = (ox) - this.getCenterPosition().getX();
	    double dy = (oy) - this.getCenterPosition().getY();
	    double prevAngle = this.getAngle();
	    
	    double angle = Math.toDegrees(Math.atan(dy / dx));
	    this.setAngle(angle);
	    return angle-prevAngle;
	}

}
