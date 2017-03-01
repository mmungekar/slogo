package back_end.commands.abstracts;

import back_end.commands.presetLibrary.CommandInterface;

public abstract class NoParameterCommand implements CommandInterface{
	@Override
	public void setParameters(double... ds) {
		// do nothing
	}
}
