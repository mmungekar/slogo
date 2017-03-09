package back_end.model.scene;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Turtle {
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "turtle.gif";
	private ImageView myImageView;
	
	private Point2D topLeftPos;
	private Point2D centerPos = null;
	private Point2D prevCenterPos = null;
	private boolean penDown;
	private double[] halfDems = new double[2];
	private double angle;
	private Color penColor = Color.BLACK;

	Turtle(Point2D initPos) {		
		myImageView = new ImageView(getTurtleImage(DEFAULT_TURTLE));
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

	Turtle(double x, double y) {
		this(new Point2D(x, y));
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

	public double setAngle(double newAngle) {
		double oldAngle = this.angle;
		this.angle = newAngle;
		keepAngleWithin360();
		myImageView.setRotate(this.angle);
		return newAngle - oldAngle;
	}

	private void keepAngleWithin360() {
		if (Math.abs(this.angle) >= 360){
			this.angle -= Math.signum(this.angle) * 360;
		}
	}

	public double changeImage(String newTurtleImagePath) {
		myImageView.setImage(getTurtleImage(newTurtleImagePath));
		calcHalfDems();
		setPosition(this.centerPos);
		return 0;
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

	public double setPenColor(Color newColor) {
		this.penColor = newColor;
		return 0;
	}

	public boolean isVisible() {
		return myImageView.isVisible();
	}

	public double calcDistanceFromPos(Point2D pos) {
		return Math.sqrt(Math.pow((pos.getX() - this.getCenterPosition().getX()), 2) + Math.pow((pos.getY() - this.getCenterPosition().getY()), 2)); 
	}

	public double setVisible(boolean b) {
		myImageView.setVisible(b);
		return 0;
	}

	public Node getImageView() {
		return myImageView;
	}
	
	public double moveForward(double mag) {
		double angle = this.getAngle();
		double dx = Math.cos(Math.toRadians(angle)) * mag;
		double dy = Math.sin(Math.toRadians(angle)) * mag;
		this.setPosition(this.getCenterPosition().add(dx, dy));	
		return mag;
	}
	
	public double setTowards(double ox, double oy) {
		double dx = (ox) - this.getCenterPosition().getX();
	    double dy = (oy) - this.getCenterPosition().getY();
	    System.out.println(new Point2D(dx, dy));
	    double prevAngle = this.getAngle();
	    double angle = Math.toDegrees(Math.atan2(dy, dx));
	    this.setAngle(angle);
	    return angle-prevAngle;
	}

	public double setPen(boolean b) {
		this.penDown = b;
		return 0;
	}
	
	public void setPenDown() {
		this.penDown = true;
	}
	
	public void setPenUp() {
		this.penDown = false;
	}

	private Image getTurtleImage(String imagePath) {
		String imageLocation = IMAGE_DIRECTORY + imagePath;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		return imageTurtle;
	}
}
