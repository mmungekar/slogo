package back_end.commands.commandLibrary.math;

import java.util.function.BinaryOperator;
import back_end.commands.commandLibrary.ProgressiveParameterCommand;
import back_end.interfaces.CommandInterface;

public class Power extends ProgressiveParameterCommand implements CommandInterface{

	@Override
	protected BinaryOperator<Double> getProgressiveAction() {
		return (a,b) -> Math.pow(a, b);
	}



}
