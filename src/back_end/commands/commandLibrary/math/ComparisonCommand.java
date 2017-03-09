package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import back_end.commands.commandLibrary.BiFunctionCommand;
import back_end.commands.commandLibrary.FunctionCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class ComparisonCommand extends FunctionCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return inputs -> getComparison().test(inputs.get(0), inputs.get(1)) ? 1.0 : 0.0;
	}
	
	protected abstract BiPredicate<Double, Double> getComparison();

}
