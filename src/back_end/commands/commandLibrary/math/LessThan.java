package back_end.commands.commandLibrary.math;

import java.util.function.BiPredicate;
import back_end.interfaces.CommandInterface;

public class LessThan extends ComparisonCommand implements CommandInterface{

	@Override
	protected BiPredicate<Double, Double> getComparison() {
		return (input1, input2) -> input1 < input2;
	}

}
