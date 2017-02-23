package commands;

import java.util.Scanner;

import back_end.ModelState;

public class RotationCommand implements CommandInterface
{
	private ParameterContainer myParameter;
	private int scale;

	public RotationCommand(boolean b)
	{
		myParameter = new MovementParameters();
		if (b){
			scale = 1;
		} else {
			scale = -1;
		}
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
		((MovementParameters)myParameter).setMovementMagnitude(scale * scanner.nextInt());
		scanner.close();
	}

	@Override
	public void Execute(ModelState state)
	{
		state.setAngle(state.getAngle() + ((MovementParameters)myParameter).getMovementMagnitude());
	}
}
