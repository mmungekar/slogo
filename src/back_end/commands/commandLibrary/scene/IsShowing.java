package back_end.commands.commandLibrary.scene;

import java.util.function.Function;

import back_end.commands.commandLibrary.CheckSupplierCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Turtle;

public class IsShowing extends CheckSupplierCommand implements CommandInterface{

	@Override
	protected Function<Turtle, Boolean> getBooleanCheck() {
		return turtle -> turtle.isVisible();
	}
}
