package back_end.commands.commandLibrary;

import java.util.List;
import java.util.function.Function;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class AllParameterCommand extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Function<List<Double>,Double> action = this.supplyAction(model);
		return action.apply(this.getParameterValues());
	}
	
	
}
