package back_end.commands.commandLibrary.turtle;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
/**
 * Clears all turtle stamps from the canvas
 * @author Mina
 *
 */

public class ClearStamps extends SimpleParameterCommand implements CommandInterface {

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		return model.getTurtleMaster().clearStamps();
	}

}
