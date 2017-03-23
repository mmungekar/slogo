package back_end.commands.commandLibrary.math;

import java.util.function.DoubleUnaryOperator;

import back_end.interfaces.CommandInterface;

public class Cosine extends OneInputMathCommand implements CommandInterface{

	@Override
	protected DoubleUnaryOperator getOperation() {
		return input -> Math.cos(Math.toRadians(input));
	}


}