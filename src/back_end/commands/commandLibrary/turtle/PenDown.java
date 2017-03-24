package back_end.commands.commandLibrary.turtle;

import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class PenDown extends NoInputTurtleCommand implements CommandInterface{

	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {
		return turtle -> {
			turtle.setPen(true); 
			return 1.0;
			};
	}
	

}

