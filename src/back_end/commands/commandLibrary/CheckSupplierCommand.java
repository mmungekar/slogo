package back_end.commands.commandLibrary;

import java.util.List;
import java.util.function.Function;

import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public abstract class CheckSupplierCommand extends SupplierCommand{

	@Override
	public double Execute(Model model) {
		Function<Turtle, Boolean> booleanCheck = getBooleanCheck();
		return model.operateOnTurtle(turtle -> {
			return (booleanCheck.apply(turtle) == true) ? 1.0 : 0.0;
		});
	}

	protected abstract Function<Turtle, Boolean> getBooleanCheck();
}
