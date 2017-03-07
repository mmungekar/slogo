package back_end.commands.commandLibrary.multipleTurtles;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Ask extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return 0;
		//return model.getSingleActiveTurtle();
	}
}
