package back_end.commands.commandLibrary.turtle;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class IsPenDown extends NoInputTurtleCommand implements CommandInterface{
	
	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {
		return turtle -> {
			return (turtle.isPenDown() == true) ? 1.0 : 0.0;
			};
	}

}
