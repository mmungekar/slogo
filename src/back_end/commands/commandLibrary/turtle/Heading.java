package back_end.commands.commandLibrary.turtle;

import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class Heading extends NoInputTurtleCommand implements CommandInterface{

	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {
		return turtle -> {
			return -1 * turtle.getAngle();
			};
	}
}
