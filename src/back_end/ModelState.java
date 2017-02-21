package back_end;

public class ModelState
{
	public static final int HEIGHT = 600, WIDTH = 700;
	private double x, y;
	
	public ModelState()
	{
		x = HEIGHT/2;
		y = WIDTH/2;
	}
	
	public void setX(double inX)
	{
		x = inX;
	}
	public void setY(double inY)
	{
		y = inY;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
}
