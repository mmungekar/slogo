package commands;

import java.util.Scanner;

import back_end.ModelState;

public class ForwardCommand implements Command
{
	private ParameterContainer parameters;
	
	public ForwardCommand()
	{
		parameters = new MovementParameters();
	}
	@Override
	public void execute(ModelState state)
	{
		state.setY(0, state.getY(0)-((MovementParameters)parameters).getMovementMagnitude());
		
	}
	@Override
	public void setParameters(String nextLine)
	{
		Scanner scanner = new Scanner(nextLine);
		((MovementParameters) parameters).setMovementMagnitude(scanner.nextInt());
		scanner.close();
	}

}
