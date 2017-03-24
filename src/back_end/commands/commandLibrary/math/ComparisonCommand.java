// This entire file is part of my masterpiece.
// MIGUEL ANDERSON
// NetID: mra21

package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.BiPredicate;
import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class ComparisonCommand extends SimpleParameterCommand implements CommandInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * back_end.commands.commandLibrary.SimpleParameterCommand#Execute(back_end.
	 * model.scene.Model)
	 * 
	 * scans through input values and stages two input values to be checked by
	 * the comparison provided by the subclasses via getComparison
	 * 
	 * I believe this is good code because it allows the subclasses to have one
	 * method which is the specific comparison it is responsible for.
	 * 
	 * I like this approach (and I used it for OneInputMathCommand and
	 * TurtleSommand) as the concrete command subclasses are single focused and
	 * short. This also means that commands are closed and fixed.
	 * 
	 */
	@Override
	public double Execute(Model model) {
		BiPredicate<Double, Double> myPredicate = getComparison();

		List<Double> inputs = this.getParameters();
		for (int i = 1; i < inputs.size(); i++) {
			if (!myPredicate.test(inputs.get(i - 1), inputs.get(i))) {
				return 0.0;
			}
		}
		return 1.0;
	}

	/**
	 * @return the boolean comparison check to be performed on the two input
	 *         values
	 */
	protected abstract BiPredicate<Double, Double> getComparison();
}
