package back_end.commands.commandLibrary;

import back_end.interfaces.CommandInterface;

public abstract class FunctionCommand extends DistributiveParameterCommand implements CommandInterface {
	@Override
	protected int getInputNumber() {
		return 1;
	}
}
