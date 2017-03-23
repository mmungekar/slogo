package back_end.commands.commandLibrary.turtle;

import java.util.function.Function;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
/**
 * By Miguel Anderson
 */
public abstract class NoInputTurtleCommand extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		return model.getTurtleMaster().operateOnTurtle(supplyAction(model));
	}

	protected abstract Function<Turtle,Double> supplyAction(Model model);
}
