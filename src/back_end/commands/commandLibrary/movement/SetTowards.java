package back_end.commands.commandLibrary.movement;

import java.util.List;
import java.util.function.Function;

import back_end.commands.commandLibrary.BiFunctionCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class SetTowards extends BiFunctionCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return (inputs) -> model.operateOnTurtle(turtle -> {
			double newX = model.getHome().getX() + inputs.get(0);
			double newY = model.getHome().getY() - inputs.get(1);
			return turtle.setTowards(newX, newY);
		});
	}

}
