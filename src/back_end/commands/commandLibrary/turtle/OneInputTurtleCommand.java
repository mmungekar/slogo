package back_end.commands.commandLibrary.turtle;

import back_end.interfaces.CommandInterface;

public abstract class OneInputTurtleCommand extends TurtleCommand implements CommandInterface{
	public static final Double FUNCTION_INPUT_NUMBER = 1d;

	@Override
	protected Double getFunctionInputNumber() {
		return 1d;
	}


}
