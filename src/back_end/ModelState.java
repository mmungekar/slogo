package back_end;

import java.util.HashMap;
import java.util.Observable;

import javafx.scene.paint.Color;


public class ModelState extends Observable
{
	HashMap<Integer, TurtleState> turtleContainer;
	private Color backgroundColor;
	
	
	public ModelState()
	{
		turtleContainer.put(0, new TurtleState());
		setBackgroundColor(Color.WHITE);
	}
	
	public void setX(int ID, double inX)
	{
		 turtleContainer.get(ID).setX(inX);
	}
	public void setY(int ID, double inY)
	{
		turtleContainer.get(ID).setY(inY);
	}
	public void setAngle(int ID, double inAngle)
	{
		turtleContainer.get(ID).setAngle(inAngle);
	}
	public double getX(int ID)
	{
		return turtleContainer.get(ID).getX();
	}
	public double getY(int ID)
	{
		return  turtleContainer.get(ID).getX();
	}
	public double getAngle(int ID)
	{
		return  turtleContainer.get(ID).getAngle();
	}

	public boolean equals(ModelState other) {
		return (this.getAngle() == other.getAngle()
				&& this.getX() == other.getX()
				&& this.getY() == other.getY());
	}

	public ModelState copy() {
		ModelState copy = new ModelState();
		copy.setX(this.getX());
		copy.setY(this.getY());
		copy.setAngle(this.getAngle());
		return copy;
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
	
}
