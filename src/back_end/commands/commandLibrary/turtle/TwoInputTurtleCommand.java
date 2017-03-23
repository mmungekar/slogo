package back_end.commands.commandLibrary.turtle;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;

public abstract class TwoInputTurtleCommand extends TurtleCommand implements CommandInterface{
	
	public void checkParams() throws NotEnoughParameterException{
		if ((getParameters().size() % 2) != 0) {
			throw new NotEnoughParameterException(2);
		}
	}


	@Override
	protected Double getFunctionInputNumber() {
		return 2d;
	}	

}
