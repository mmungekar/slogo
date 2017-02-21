package commands;

public class MovementParameters implements ParameterContainer
{
	private int magnitude;
	
	public MovementParameters()
	{
		magnitude = 0;
	}
	public void setMovementMagnitude(int in)
	{
		magnitude = in;
	}
	public int getMovementMagnitude()
	{
		return magnitude;
	}
}
