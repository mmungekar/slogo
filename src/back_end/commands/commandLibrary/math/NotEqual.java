package back_end.commands.commandLibrary.math;

import java.util.function.BiPredicate;

import back_end.commands.commandLibrary.ComparisonCommand;
import back_end.interfaces.CommandInterface;

public class NotEqual extends ComparisonCommand implements CommandInterface{

	@Override
	protected BiPredicate<Double, Double> getComparison() {
		return (input1, input2) -> input1 != input2;
	}
}
