package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.Function;

import back_end.commands.commandLibrary.BiFunctionCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Sum extends BiFunctionCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return inputs -> inputs.get(0) + inputs.get(1);
	}
}
