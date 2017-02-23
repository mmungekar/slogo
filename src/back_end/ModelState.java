package back_end;

import java.util.HashMap;

public class ModelState
{
	HashMap<Integer, TurtleState> TurtleContainer;
	private double x, y;
	private double angle;
	
	public ModelState()
	{
		x = 0;
		y = 0;
		angle = 0;
	}
	
	public void setX(double inX)
	{
		x = inX;
	}
	public void setY(double inY)
	{
		y = inY;
	}
	public void setAngle(double inAngle)
	{
		angle = inAngle;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getAngle()
	{
		return angle;
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
	
	@Override
	public String toString(){
		return ("X: " + this.getX() + " Y: " + this.getY() + " Angle: " + this.getAngle()); 
	}
		
}
