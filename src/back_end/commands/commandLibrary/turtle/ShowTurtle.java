package back_end.commands.commandLibrary.turtle;

import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;

public class ShowTurtle extends NoInputTurtleCommand implements CommandInterface{

	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {
		return turtle -> {
			turtle.setVisible(true); 
			return 1.0;
			};
	}
	
	
	

}
