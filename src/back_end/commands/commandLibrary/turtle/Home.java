package back_end.commands.commandLibrary.turtle;

import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

import java.util.List;
import java.util.function.BiFunction;

import back_end.interfaces.CommandInterface;

public class Home extends OneInputTurtleCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {		
		return model.operateOnTurtle(turtle -> turtle.setPosition(model.getHome()));
	}

	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		return (turtle, inputs) -> {
			return turtle.setPosition(model.getHome());
			};
	}
	

}
