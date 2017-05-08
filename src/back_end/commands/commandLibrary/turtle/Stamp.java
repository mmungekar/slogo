package back_end.commands.commandLibrary.turtle;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.model.scene.Model;

public class Stamp extends SimpleParameterCommand
{
	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException
	{
		
		return 0;
	}
}
