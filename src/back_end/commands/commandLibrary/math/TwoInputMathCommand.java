// This entire file is part of my masterpiece.
// MIGUEL ANDERSON
// NetID: mra21

package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.BinaryOperator;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class TwoInputMathCommand extends SimpleParameterCommand implements CommandInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * back_end.commands.commandLibrary.SimpleParameterCommand#Execute(back_end.
	 * model.scene.Model)
	 * 
	 * Like the other super classes in my masterpiece, this class is intended to
	 * lift a lot of the work and complexity away from the concrete command
	 * classes.
	 * 
	 * In this specific class, we are looking to apply a math operation to two
	 * or more inputted parameters. When two parameters are put in, this is
	 * simple. To add two parameters, one just adds the values. For more than
	 * two it becomes more complicated. We decided to make the operation
	 * continue down all the parameters. For example ( sum 3 4 5 ) would return
	 * the sum of all the elements (i.e. 12 ). Another example is ( divide 8 4 2
	 * ) would return 1 for it goes along each element applying the operation (8
	 * / 4 = 2, then 2 / 2 = 1, return 1).
	 * 
	 * I decided to implement this approach using the neat tool of stream
	 * reduction which keeps the previous value. The reduce method needs a base
	 * value to start at which I set to the first parameter and then had the
	 * entire thing skip the first. Lastly one must give the operation to enact
	 * on each parameter which is what each subclass provides.
	 * 
	 * Again this is good code because it allows the subclasses to be singlely
	 * focused and closed. Also I think this is an effect use of java streams
	 * 
	 */
	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		List<Double> parameters = this.getParameters();
		return parameters.stream().skip(1).reduce(parameters.get(0), getOperation());
	}

	/**
	 * @return where each subclass gives the symbol math operation to the
	 *         Execute method. Here it is a BinaryOperator<Double> which means
	 *         it takes two doubles and returns a double.
	 */
	protected abstract BinaryOperator<Double> getOperation();

}
