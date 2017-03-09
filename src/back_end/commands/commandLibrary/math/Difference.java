package back_end.commands.commandLibrary.math;

import java.util.function.BinaryOperator;
import back_end.commands.commandLibrary.ProgressiveParameterCommand;
import back_end.interfaces.CommandInterface;

public class Difference extends ProgressiveParameterCommand implements CommandInterface{

	@Override
	protected BinaryOperator<Double> getProgressiveAction() {
		return (x, y) -> x - y;
	}
}
