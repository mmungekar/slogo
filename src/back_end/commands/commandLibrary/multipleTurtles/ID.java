package back_end.commands.commandLibrary.multipleTurtles;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class ID extends SimpleParameterCommand implements CommandInterface, Constant{

	@Override
	public double Execute(Model model) {
		return model.getActiveTurtleID();
	}
}