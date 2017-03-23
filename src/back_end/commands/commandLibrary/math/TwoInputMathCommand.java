package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.BinaryOperator;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class TwoInputMathCommand extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		List<Double> parameters = this.getParameters();
		return parameters.stream().skip(1).reduce(parameters.get(0), getOperation());
	}
	
	protected abstract BinaryOperator<Double> getOperation();

}
