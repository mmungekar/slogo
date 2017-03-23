package back_end.commands.commandLibrary.turtle;

import java.util.List;
import java.util.function.BiFunction;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class SetPosition extends TwoInputTurtleCommand implements CommandInterface{
	
	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		return (turtle, inputs) -> turtle.setPosition(model.getHome().getX() + inputs.get(0), model.getHome().getY() - inputs.get(1));
	}
}
