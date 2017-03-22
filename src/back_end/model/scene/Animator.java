package back_end.model.scene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.function.Function;
/**
 * ANIMATES TURTLES YAY
 */
public class Animator extends Observable implements Observer {
	private final String ANIMATOR_FILEPATH = "resources.parameters/Animator";
   private ResourceBundle AnimatorProperties = ResourceBundle.getBundle(this.ANIMATOR_FILEPATH);
	private int DEFAULT_SPEED = Integer.parseInt(AnimatorProperties.getString("DefaultSpeed"));
    private final int FRAMES_PER_SECOND = Integer.parseInt(AnimatorProperties.getString("FramesPerSecond"));
    private final int MILLISECOND_DELAY = Integer.parseInt(AnimatorProperties.getString("MilliDelayFactor")) / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = Double.parseDouble(AnimatorProperties.getString("SecondDelayFactor"))  / FRAMES_PER_SECOND;
    private Timeline mainTimeline; 
    private TurtleMaster myTurtleMaster;
    private List<Point2D> centerPositions;
    public boolean running;
    private Queue<Function<Turtle, Double>> funcQueue;
     
    /**
     * @param Model
     */
    public Animator(TurtleMaster turtleMaster){
        myTurtleMaster = turtleMaster;
        running = false;
        funcQueue = new LinkedList<Function<Turtle, Double>>();
    }
    
    private void setChangedAndNotifyObservers() {
		setChanged();
		notifyObservers();
	}
    
    /**
     * @return returns if the animator is running
     */
    public boolean isRunning(){
    	return running;
    }
    /**
     * @return returns the queue of commands received by the animator
     */
    public Queue<Function<Turtle, Double>> getQueue(){
    	return funcQueue;
    }
    
    
    /**
     * @return returns the model the animator is simulating
     */
    /*
    public TurtleMaster getTurtleMaster(){
        return this.myTurtleMaster;
    }
    */
    
    /**
     * Initializes and plays animation with default parameters
     */
    public void initialize(double magnitude){
    	running = true;
    	centerPositions = myTurtleMaster.getCenterPositions();
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
        running = true;
    }
    /**
     * Stops animation
     */
    public void stop(){
        this.mainTimeline.pause();
        running = false;
    }
    /**
     * Sets the rate of animation
     * @param rate
     */
    public void changeRate(double rate){
            this.mainTimeline.setRate(rate);
    }
    
    private double getSign(double d){
    	return (d>0)? 1:-1;
    }
    
    private void updateTurtle(Turtle myTurtle, int id,double mag, double elapsedTime){
    	ImageView turtle = setMovementParams(myTurtle, mag, elapsedTime);
		setPositions(myTurtle, turtle);
		setChangedAndNotifyObservers();
		checkValidPosition(myTurtle);
		if(checkPositionHit(myTurtle, id, mag)){
			this.stop();
			myTurtleMaster.revertActiveTurtles();
			checkQueue();
			}
    }

	private ImageView setMovementParams(Turtle myTurtle, double mag, double elapsedTime) {
		myTurtle.setXSpeed(DEFAULT_SPEED*getSign(mag)*Math.cos(Math.toRadians(myTurtle.getAngle())));
    	myTurtle.setYSpeed(DEFAULT_SPEED*getSign(mag)*Math.sin(Math.toRadians(myTurtle.getAngle())));
    	ImageView turtle = (ImageView)myTurtle.getImageView();
    	turtle.setX(turtle.getX() + myTurtle.getXSpeed()* elapsedTime);
		turtle.setY(turtle.getY() + myTurtle.getYSpeed()* elapsedTime);
		return turtle;
	}

	private void setPositions(Turtle myTurtle, ImageView turtle) {
		Point2D newPos = new Point2D(turtle.getX()+(turtle.getImage().getWidth() / 2.0), 
				turtle.getY()+(turtle.getImage().getHeight() / 2.0));
		if (myTurtle.getCenterPosition() != null) {
			myTurtle.setPrevCenterPosition(myTurtle.getCenterPosition());
		} else {
			myTurtle.setPrevCenterPosition(newPos);
		} 
		myTurtle.setCenterPosition(newPos);
		myTurtle.setTopLeftPosition(new Point2D(turtle.getX(), turtle.getY()));
	}
    
    private void step(double magnitude, double elapsedTime) {
		 myTurtleMaster.getListeningTurtleIDs().stream().filter(elt -> elt != null).forEach(id -> {
			 myTurtleMaster.setActiveTurtleID(id);
			Turtle turtle = myTurtleMaster.getTurtle(id);
			updateTurtle(turtle,id, magnitude, elapsedTime);
		});
	}
    
    private boolean checkQueue(){
    	while(funcQueue.size()!=0){
    		if(running){
    			return false;
    			}
    		myTurtleMaster.operateOnTurtle(funcQueue.remove());
    	}
    	return true;
    }
    
    private boolean checkPositionHit(Turtle turtle, int id, double mag){
    	return turtle.calcDistanceFromPos(centerPositions.get(id))>=Math.abs(mag);
    }
    
    public void checkValidPosition(Turtle turtle){
    	ImageView turtleView = (ImageView)turtle.getImageView();
    	int xMax = Integer.parseInt(AnimatorProperties.getString("CanvasXMax"));
    	int yMax = Integer.parseInt(AnimatorProperties.getString("CanvasYMax"));
    	if(turtleView.getX()<0||turtleView.getX()>xMax|turtleView.getY()<0||turtleView.getY()>yMax){
    		this.stop();
    		funcQueue.clear();
    	}
    }
    
	@Override
	public void update(Observable arg0, Object arg1) {
		if(!running){
			initialize(((Turtle) arg0).getMagnitude());
		}
	}
}