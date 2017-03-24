// This entire file is part of my masterpiece.
// MIGUEL ANDERSON
// NetID: mra21

// This is an example of how ComparisonCommand is implemented

package back_end.commands.commandLibrary.math;

import java.util.function.BiPredicate;

import back_end.interfaces.CommandInterface;

public class LessThan extends ComparisonCommand implements CommandInterface {
	@Override
	protected BiPredicate<Double, Double> getComparison() {
		return (a, b) -> a < b;
	}
}
