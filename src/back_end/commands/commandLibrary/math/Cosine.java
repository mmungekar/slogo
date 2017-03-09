package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.Function;

import back_end.commands.commandLibrary.FunctionCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Cosine extends MathFunctionCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return inputs -> Math.cos(Math.toRadians(inputs.get(0)));
	}

}
