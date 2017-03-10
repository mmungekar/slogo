package back_end.commands.commandLibrary.scene;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.commandLibrary.turtle.NoInputTurtleCommand;
import back_end.commands.commandLibrary.turtle.OneInputTurtleCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.NotInMapException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class GetPenColor extends NoInputTurtleCommand implements CommandInterface {

	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {		
		return turtle -> {
			try {
				return model.getDrawer().getIndexFromColor(turtle.getPenColor());
			} catch (NotInMapException e) {
				model.sendToOutput(e.getMessage());
				return -1.0;
			}
		};
	}
	
}
