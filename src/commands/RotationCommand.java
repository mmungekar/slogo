package commands;

import java.util.Scanner;

import back_end.ModelState;

public class RotationCommand implements Command
{
	private ParameterContainer myParameter;
	private int parameterCount = 1;

	public RotationCommand()
	{
		myParameter = new MovementParameters();
	}
	/**
	 *  Sets the factor by which the turtle needs to rotate so that the Model can simply
	 *  call setRotate 
	 *  @param Scanner and string type are passed from StringInterpreter so that the 
	 *  remainder of the user-entered command can be parsed here
	 */
	@Override
	public void setParameters (String nextLine)
	{
		Scanner scanner = new Scanner(nextLine);
		((MovementParameters)myParameter).setMovementMagnitude(scanner.nextInt());
		scanner.close();
	}

	@Override
	public void Execute(ModelState state)
	{
		state.setAngle(((MovementParameters)myParameter).getMovementMagnitude());
	}
	
	@Override
	public int getParameterCount(){
		return this.parameterCount;
	}
}
