package back_end.commands.commandLibrary.math;

import java.util.function.BiPredicate;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class And extends ComparisonCommand implements CommandInterface{

	@Override
	protected BiPredicate<Double, Double> getComparison() {
		return (a, b) -> (a != 0 && b != 0);
	}
}