package back_end;

import java.util.Scanner;

import back_end.ModelState;
import commands.Command;
import commands.MovementParameters;
import commands.ParameterContainer;

public class MovementCommand implements Command
{
	private ParameterContainer parameters;
	
	public MovementCommand()
	{
		parameters = new MovementParameters();
	}
	@Override
	public void Execute(ModelState state)
	{
		state.setY(state.getY()-((MovementParameters)parameters).getMovementMagnitude());
		
	}
	@Override
	public void setParameters(String nextLine)
	{
		Scanner scanner = new Scanner(nextLine);
		((MovementParameters) parameters).setMovementMagnitude(scanner.nextInt());
		scanner.close();
	}

}