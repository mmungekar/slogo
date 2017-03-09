package back_end.commands.commandLibrary;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class ProgressiveParameterCommand extends AllParameterCommand implements CommandInterface{
	@Override
	protected Function<List<Double>,Double> supplyAction(Model model){
		return inputs -> inputs.stream().skip(1).reduce(inputs.get(0), getProgressiveAction());
	}
	
	protected abstract BinaryOperator<Double> getProgressiveAction();

}
