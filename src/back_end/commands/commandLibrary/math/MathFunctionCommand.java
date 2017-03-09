package back_end.commands.commandLibrary.math;

import java.util.function.DoubleBinaryOperator;

import back_end.commands.commandLibrary.FunctionCommand;
import back_end.interfaces.CommandInterface;

public abstract class MathFunctionCommand extends FunctionCommand implements CommandInterface{

	@Override
	protected DoubleBinaryOperator getHowToHandlePreviousValue() {
		return (prevValue, result) -> prevValue;
	}
}
