package front_end;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Canvas {
	private Rectangle Frame;
	public static final int arc = 25;

	public Canvas(Group root) {
		createRectangle();

		root.getChildren().add(Frame);
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

}
