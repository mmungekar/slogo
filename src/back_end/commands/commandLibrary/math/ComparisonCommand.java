package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.BiPredicate;
import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class ComparisonCommand extends SimpleParameterCommand implements CommandInterface {

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

	protected abstract BiPredicate<Double, Double> getComparison();
}
