package back_end.commands.commandLibrary.turtle.multiple;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Turtles extends SimpleParameterCommand implements CommandInterface, Constant{

	@Override
	public double Execute(Model model) {
		return model.getTurtleMaster().getAllTurtleIDs().size();
	}
}
