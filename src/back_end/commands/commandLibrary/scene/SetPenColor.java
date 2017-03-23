package back_end.commands.commandLibrary.scene;

import java.util.List;
import java.util.function.BiFunction;

import back_end.commands.commandLibrary.turtle.OneInputTurtleCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class SetPenColor extends OneInputTurtleCommand implements CommandInterface{

	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		return (turtle, inputs) -> {
			turtle.setPenColor(model.getDrawer().getColorFromIndex(inputs.get(0).intValue())); 
			return inputs.get(0);
			};
	}

}
