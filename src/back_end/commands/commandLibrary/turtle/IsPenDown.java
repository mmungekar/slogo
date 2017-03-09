package back_end.commands.commandLibrary.turtle;

import java.util.List;
import java.util.function.BiFunction;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class IsPenDown extends OneInputTurtleCommand implements CommandInterface{
	
	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		return (turtle, inputs) -> {
			return (turtle.isPenDown() == true) ? 1.0 : 0.0;
			};
	}

}
