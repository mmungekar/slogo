package commands;

import java.util.Scanner;

import back_end.ModelState;

public class ForwardCommand implements Command
{
	public static final String X = "X";
	public static final String Y = "Y";
	
	
	private ParameterContainer parameters;
	private int scale;
	
	public ForwardCommand(boolean b)
	{
		parameters = new MovementParameters();
		if (b){
			scale = 1;
		} else {
			scale = -1;
		}
		
	}
	@Override
	public void Execute(ModelState state)
	{
		moveForward(state, ((MovementParameters)parameters).getMovementMagnitude());		
	}
	
	private void moveForward(ModelState state, int movementMagnitude) {
		double xDis = calculateComponentMovementMagnitude(state.getAngle(), movementMagnitude, X);
		double yDis = calculateComponentMovementMagnitude(state.getAngle(), movementMagnitude, Y);
		
		state.setX(state.getX() - xDis);
		state.setY(state.getY() - yDis);
		
	}
	private double calculateComponentMovementMagnitude(double angle, int movementMagnitude, String string) {
		if (string.equals(X)){
			return movementMagnitude * Math.sin(Math.toRadians(angle));
		} else if (string.equals(Y)){
			return movementMagnitude * Math.cos(Math.toRadians(angle));
		}
		return movementMagnitude;
	}
	@Override
	public void setParameters(String nextLine)
	{
		Scanner scanner = new Scanner(nextLine);
		((MovementParameters) parameters).setMovementMagnitude(scale * scanner.nextInt());
		scanner.close();
	}

}
