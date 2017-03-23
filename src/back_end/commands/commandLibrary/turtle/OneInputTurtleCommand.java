package back_end.commands.commandLibrary.turtle;

import back_end.interfaces.CommandInterface;
/**
 * By Miguel Anderson
 */
public abstract class OneInputTurtleCommand extends TurtleCommand implements CommandInterface{
	public static final Double FUNCTION_INPUT_NUMBER = 1d;

	@Override
	protected Double getFunctionInputNumber() {
		return FUNCTION_INPUT_NUMBER;
	}


}
