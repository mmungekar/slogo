package back_end.commands.commandLibrary.multipleTurtles;

import back_end.commands.commandLibrary.NoInputCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Turtles extends NoInputCommand implements CommandInterface, Constant{

	@Override
	public double Execute(Model model) {
		return model.getTurtleCount();
	}

	
}
