package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.Function;

import back_end.commands.commandLibrary.OneInputCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class ArcTangent extends OneInputCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return inputs -> Math.toDegrees(Math.atan(inputs.get(0)));
	}

}
