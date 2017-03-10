package back_end.model.scene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.chart.LineChart;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.function.Function;
/**
 * ANIMATES TURTLES YAY
 */
public class Animator extends Observable implements Observer {
  // private final String UITAGS_FILEPATH = "src.ui.property/UITags";
   // private ResourceBundle AnimatorProperties = ResourceBundle.getBundle(this.UITAGS_FILEPATH);
	private double turtleXSpeed, turtleYSpeed;
    private final int FRAMES_PER_SECOND = 60;//Integer.parseInt(AnimatorProperties.getString("FramesPerSecond"));
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Timeline mainTimeline; 
    private TurtleMaster myTurtleMaster;
    private List<Point2D> centerPositions;
    public boolean isRunning;
    /**
     * @param Model
     */
    public Animator(TurtleMaster turtleMaster){
        myTurtleMaster = turtleMaster;
        isRunning = false;
    }
    
    private void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
	}
    
    /**
     * @return returns the model the animator is simulating
     */
    public TurtleMaster getTurtleMaster(){
        return this.myTurtleMaster;
    }
    /**
     * Initializes and plays animation with default parameters
     */
    public void initialize(double magnitude){
    	isRunning = true;
    	centerPositions = new ArrayList<Point2D>();
    	myTurtleMaster.getTurtleContainer().keySet().stream().forEach(id -> {
    		centerPositions.add(myTurtleMaster.getTurtleContainer().get(id).getCenterPosition());
    	});
    	this.mainTimeline = new Timeline();
        this.mainTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                g -> step(magnitude, SECOND_DELAY));
        this.mainTimeline.getKeyFrames().add(frame);
        this.mainTimeline.play();
    }
    /**
     * Plays animation
     */
    public void play(){
        this.mainTimeline.play();
        isRunning = true;
    }
    /**
     * Stops animation
     */
    public void stop(){
        this.mainTimeline.pause();
        isRunning = false;
    }
    /**
     * Sets the rate of animation
     * @param rate
     */
    public void changeRate(double rate){
            this.mainTimeline.setRate(rate);
    }
    
    private void updateTurtle(Turtle myTurtle, int id,double mag, double elapsedTime){
    	myTurtle.setXSpeed(100*Math.cos(Math.toRadians(myTurtle.getAngle())));
    	myTurtle.setYSpeed(100*Math.sin(Math.toRadians(myTurtle.getAngle())));
    	ImageView turtle = (ImageView)myTurtle.getImageView();
    	turtle.setX(turtle.getX() + myTurtle.getXSpeed()* elapsedTime);
		turtle.setY(turtle.getY() + myTurtle.getYSpeed()* elapsedTime);
		Point2D newPos = new Point2D(turtle.getX()+(turtle.getImage().getWidth() / 2.0), 
				turtle.getY()+(turtle.getImage().getHeight() / 2.0));
		if (myTurtle.getCenterPosition() != null) {
			myTurtle.setPrevCenterPosition(myTurtle.getCenterPosition());
		} else {
			myTurtle.setPrevCenterPosition(newPos);
		} 
		myTurtle.setCenterPosition(newPos);
		setChangedAndNotifyObservers();
		if(checkPositionHit(myTurtle, id, mag)){
			this.stop();
			}
		myTurtle.setTopLeftPosition(new Point2D(turtle.getX(), turtle.getY()));
    }
    
    private void step(double magnitude, double elapsedTime) {
		List<Double> results = new ArrayList<Double>();
		 myTurtleMaster.getListeningTurtleIDs().stream().filter(elt -> elt != null).forEach(id -> {
			//activeTurtleID = id;
			Turtle turtle = myTurtleMaster.getTurtleContainer().get(id);
			updateTurtle(turtle,id, magnitude, elapsedTime);
		});
	}
    
    private boolean checkPositionHit(Turtle turtle, int id, double mag){
    	System.out.println(turtle.calcDistanceFromPos(centerPositions.get(id)));
    	System.out.println(mag);
    	System.out.println(turtle.calcDistanceFromPos(centerPositions.get(id))>=mag);
    	return turtle.calcDistanceFromPos(centerPositions.get(id))>=mag;
    }
    
	@Override
	public void update(Observable arg0, Object arg1) {
		if(!isRunning){
			initialize(((Turtle) arg0).getMagnitude());
		}
	}
}