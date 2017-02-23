package back_end;

public class TurtleState
{
	private double x, y, angle;
	private boolean penUp;
	
	public TurtleState()
	{
		setX(0);
		setY(0);
		setAngle(0);
		setPenUp(true);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public boolean isPenUp() {
		return penUp;
	}

	public void setPenUp(boolean penUp) {
		this.penUp = penUp;
	}
}
