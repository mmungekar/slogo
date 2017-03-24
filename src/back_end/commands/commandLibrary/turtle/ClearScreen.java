package back_end.commands.commandLibrary.turtle;

import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

import java.util.function.Function;

import back_end.interfaces.CommandInterface;

public class ClearScreen extends NoInputTurtleCommand implements CommandInterface{	
	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {
		return turtle -> {
			double result = turtle.setPosition(model.getHome());
			turtle.dontDrawLine();
			return result;
		};
	}


}
