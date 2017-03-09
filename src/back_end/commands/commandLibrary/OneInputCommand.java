package back_end.commands.commandLibrary;

import back_end.interfaces.CommandInterface;

public abstract class OneInputCommand extends DistributiveParameterCommand implements CommandInterface {
	
	private static final int INPUT_NUMBER = 1;

	@Override
	protected int getInputNumber() {
		return INPUT_NUMBER;
	}
}
