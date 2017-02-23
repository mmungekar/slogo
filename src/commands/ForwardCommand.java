package commands;

import java.util.Scanner;

import back_end.ModelState;

public class ForwardCommand implements Command
{
	private ParameterContainer parameters;
	private int parameterCount = 1;
	
	public ForwardCommand()
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
	@Override
	public int getParameterCount(){
		return this.parameterCount;
	}

}
