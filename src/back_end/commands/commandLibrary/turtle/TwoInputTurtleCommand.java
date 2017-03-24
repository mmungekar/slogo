package back_end.commands.commandLibrary.turtle;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;

/**
 * By Miguel Anderson
 */
public abstract class TwoInputTurtleCommand extends TurtleCommand implements CommandInterface{
	public static final Double FUNCTION_INPUT_NUMBER = 2d;
	
	public void checkParams() throws NotEnoughParameterException{
		if ((getParameters().size() % 2) != 0) {
			throw new NotEnoughParameterException(2);
		}
	}
	
	@Override
	protected Double getFunctionInputNumber() {
		return FUNCTION_INPUT_NUMBER;
	}

}
