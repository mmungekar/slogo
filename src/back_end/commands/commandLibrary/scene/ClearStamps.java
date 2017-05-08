package back_end.commands.commandLibrary.scene;

import java.util.function.Function;

import back_end.commands.commandLibrary.turtle.NoInputTurtleCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class ClearStamps extends NoInputTurtleCommand implements CommandInterface{

	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {
		return turtle -> {
			if(model.getDrawer().getStamps().size() == 0)
				return (double) 0;
			else {
				model.getDrawer().clearStamp();
				return (double) 1;
			}
		};
	}

}
