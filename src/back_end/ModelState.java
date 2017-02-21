package back_end;

import java.util.HashMap;

public class ModelState
{
	public static final int HEIGHT = 600, WIDTH = 700;
	HashMap<Integer, TurtleState> TurtleContainer;
	private double x, y;
	private double angle;
	
	public ModelState()
	{
		x = HEIGHT/2;
		y = WIDTH/2;
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
