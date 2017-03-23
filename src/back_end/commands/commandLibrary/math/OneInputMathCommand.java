// This entire file is part of my masterpiece.
// MIGUEL ANDERSON

package back_end.commands.commandLibrary.math;

import java.util.function.DoubleUnaryOperator;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class OneInputMathCommand extends SimpleParameterCommand implements CommandInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * back_end.commands.commandLibrary.SimpleParameterCommand#Execute(back_end.
	 * model.scene.Model)
	 * 
	 * single input math functions (e.g. cosine, sine, tangent) only act on the
	 * first parameter entered and therefore we extract the first parameter and
	 * then ask the subclass for the math operation is necessary.
	 * 
	 * Again, this approach allows the commands to be singly-focused and this
	 * class to do all the work. This also means that commands are closed and
	 * fixed.
	 * 
	 */
	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Double parameter = this.getParameters().get(0);
		return getOperation().applyAsDouble(parameter);
	}

	/**
	 * @return a function that takes in a double and returns a double
	 * 
	 *         All single input math commands take are single input math
	 *         functions. In other words, they are DoubleUnaryOperators. The
	 *         subclasses supply these and therfore they are closed.
	 * 
	 */
	protected abstract DoubleUnaryOperator getOperation();

}
