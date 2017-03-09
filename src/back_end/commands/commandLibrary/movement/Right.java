package back_end.commands.commandLibrary.movement;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class Right extends RotationCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return supplyRotateAction(model, angleChange(1));
	}
	
}
