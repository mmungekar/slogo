package back_end.commands.commandLibrary;

import java.util.List;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class SupplierCommand extends SimpleParameterCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return null;
	}

	@Override
	protected int getInputNumber() {
		return 0;
	}

}
