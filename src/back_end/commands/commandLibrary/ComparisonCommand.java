package back_end.commands.commandLibrary;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class ComparisonCommand extends AllParameterCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		BiPredicate<Double, Double> myPredicate = getComparison();
		return inputs -> {
			for (int i = 1 ; i < inputs.size(); i++){
				if(!myPredicate.test(inputs.get(i -1), inputs.get(i))){
					return 0.0;
				}
			}
			return 1.0;
		};
	}
	
	protected abstract BiPredicate<Double, Double> getComparison();

}
