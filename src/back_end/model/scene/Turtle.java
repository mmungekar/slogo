package back_end.model.scene;

import java.util.Observable;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
/**
 * By Miguel Anderson, Juan Philippe
 * Modified by Mina Mungekar
 */
public class Turtle extends Observable {
	private final String ANIMATOR_FILEPATH = "resources.parameters/Animator";
	private final ResourceBundle AnimatorProperties = ResourceBundle.getBundle(this.ANIMATOR_FILEPATH);
	private final int xMax = Integer.parseInt(AnimatorProperties.getString("RelativeXMax"));
	private final int yMax = Integer.parseInt(AnimatorProperties.getString("RelativeYMax"));
	public static final String IMAGE_DIRECTORY = "resources/images/";
	public static final String DEFAULT_TURTLE = "turtle.gif";
	public static final Double DEFAULT_PEN_SIZE = 2.0;
	public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	public static final String PRINT_STATUS_FORMAT = "Location: (%1$f, %2$f) Heading: (%3$f) PenDown: (%4$f)";
	private ImageView myImageView;
	
	private Point2D topLeftPos;
	private Point2D centerPos = null;
	private Point2D prevCenterPos = null;
	private double magnitude;
	private boolean penDown;
	private double[] halfDems = new double[2];
	private double angle;

	private Color penColor;
	private double penSize;
	private double xSpeed, ySpeed;

	Turtle(Point2D initPos) {		
		myImageView = new ImageView(getTurtleImage(DEFAULT_TURTLE));
		this.setAngle(0);
		calcHalfDems();
		setAngle(-90);
		setPosition(initPos);
		penColor = DEFAULT_PEN_COLOR;
		penDown = true;
		penSize = DEFAULT_PEN_SIZE;
	}

	public void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
	}
	
	public double getXSpeed(){
		return xSpeed;
	}
	
	public double getYSpeed(){
		return ySpeed;
	}
	
	public void setXSpeed(double speed){
		xSpeed = speed;
	}
	
	public void setYSpeed(double speed){
		ySpeed = speed;
	}
	
	private void calcHalfDems() {
		halfDems[0] = myImageView.getImage().getWidth() / 2.0;
		halfDems[1] = myImageView.getImage().getHeight() / 2.0;
	}

	Turtle(double x, double y) {
		this(new Point2D(x, y));
	}

	public double setPosition(double inX, double inY) {
		if(inX<-xMax||inX>xMax||inY<-yMax||inY>yMax){
    		return 0;
		}
		setPenUp();
		return setPosition(new Point2D(inX, inY));
	}
	
	public double getMagnitude() {
		return magnitude;
	}

	public double setPosition(double mag) {
		magnitude = mag;
		setChangedAndNotifyObservers();
		return magnitude;
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
	
	public Point2D getRelativePosition(Point2D reference){
		return this.centerPos.subtract(reference);
	}
	
	
	public Point2D getCenterPosition() {
		return this.centerPos;
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
		while (Math.abs(this.angle) >= 360){
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

	public double setVisible(boolean b) {
		myImageView.setVisible(b);
		return 0;
	}

	public ImageView getImageView() {
		return myImageView;
	}
	
	public double setTowards(double ox, double oy) {
		Point2D distanceVector = new Point2D(ox, oy).subtract(centerPos);
	    double prevAngle = this.getAngle();
	    double angle = Math.toDegrees(Math.atan2(distanceVector.getX(), distanceVector.getY()));
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

	public void setPenSize(Double double1) {
		this.penSize = double1;
	}
	
	public Double getPenSize(){
		return this.penSize;
	}

	public Image getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setShape(Image image) {
		myImageView.setImage(image);
		calcHalfDems();
		setPosition(this.centerPos);		
	}

	public String printStatus(Point2D home) {
		return String.format(PRINT_STATUS_FORMAT, 
				this.centerPos.subtract(home).getX(),
				-this.centerPos.subtract(home).getY(), 
				-this.getAngle(), 
				this.penDown? 1.0 : 0.0); 
	}
	
	public void setTopLeftPosition(Point2D point) {
		this.topLeftPos = point;
	}
	
	public void setPrevCenterPosition(Point2D point) {
		this.prevCenterPos = point;
	}
	public void setCenterPosition(Point2D point) {
		this.centerPos = point;
	}
	public Point2D getTopLeftPosition(Point2D point) {
		return this.topLeftPos;
	}

	public double calcDistanceFromPos(Point2D pos) {
		return Math.sqrt(Math.pow((pos.getX() - this.getCenterPosition().getX()), 2) + Math.pow((pos.getY() - this.getCenterPosition().getY()), 2)); 
	}
}
