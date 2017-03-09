package back_end.commands.commandLibrary.movement;

import java.util.List;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Left extends RotationCommand implements CommandInterface{
	
	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return supplyRotateAction(model, angleChange(-1));
	}

	
	
}
