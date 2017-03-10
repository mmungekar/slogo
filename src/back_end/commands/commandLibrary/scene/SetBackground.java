package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class SetBackground extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		model.setBackgroundColor(this.getParameters().get(0).intValue());
		return this.getParameters().get(0);
	}

}
