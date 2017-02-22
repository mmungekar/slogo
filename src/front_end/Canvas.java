package front_end;

import back_end.ModelState;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Canvas {
	private Rectangle Frame;
	private Turtle myTurtle;
	public static final int arc = 25;
	
	public static final String IMAGE_SRC_FOLDER = "resources/images/";
	public static final String BALL_IMAGE = "ball.gif";
	public static final String BALL_IMAGE_FIRE = "ball_fire.gif";
	public static final String BALL_IMAGE_GIVEN = "ball_given.gif";

	public Canvas(Group root, Point2D home) {
		createRectangle();
		root.getChildren().add(Frame);
		String imageLocation = IMAGE_SRC_FOLDER + BALL_IMAGE_GIVEN;
		Image imageTurtle = new Image(getClass().getClassLoader().getResourceAsStream(imageLocation));
		myTurtle = new Turtle(imageTurtle, home);
		root.getChildren().add(myTurtle);
	}

	private void createRectangle() {
		Frame = new Rectangle();
		Frame.setStroke(Color.BLACK);
		Frame.setStrokeWidth(10);
		Frame.setArcHeight(arc);
		Frame.setArcWidth(arc);
	}

	public void setBackgroundColor(Color input) {
		Frame.setFill(input);
	}

	public void setPosition(int[] pos) {
		Frame.setX(pos[0]);
		Frame.setY(pos[1]);
	}

	public void setSize(int[] size) {
		Frame.setWidth(size[0]);
		Frame.setHeight(size[1]);
	}

	void update(ModelState state) {
		// TODO Auto-generated method stub
		
	}
}