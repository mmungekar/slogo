package back_end.commands.commandLibrary.turtle;
import java.util.List;
import java.util.function.BiFunction;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
/**
 * By Miguel Anderson
 */
public abstract class LeftRight extends OneInputTurtleCommand implements CommandInterface{

	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		return (turtle, inputs) -> turtle.setAngle(turtle.getAngle() + getDirection() * inputs.get(0));
	}

	protected abstract Double getDirection();
}
