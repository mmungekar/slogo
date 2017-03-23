package back_end.commands.commandLibrary.math;

import java.util.function.DoubleUnaryOperator;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class OneInputMathCommand extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Double parameter = this.getParameters().get(0);
		return getOperation().applyAsDouble(parameter);
	}
	
	protected abstract DoubleUnaryOperator getOperation();

}

