// This entire file is part of my masterpiece.
// MIGUEL ANDERSON
// NetID: mra21

// This is an example of how TwoInputMathCommand is implemented

package back_end.commands.commandLibrary.math;

import java.util.function.BinaryOperator;

import back_end.interfaces.CommandInterface;

public class Power extends TwoInputMathCommand implements CommandInterface{

	@Override
	protected BinaryOperator<Double> getOperation() {
		return (a,b) -> Math.pow(a, b);
	}

}
