package back_end.commands.commandLibrary.turtle;

import back_end.interfaces.CommandInterface;

public abstract class OneInputTurtleCommand extends TurtleCommand implements CommandInterface{

	@Override
	protected Double getFunctionInputNumber() {
		return 1d;
	}


}
