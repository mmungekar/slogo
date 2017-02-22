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
}
