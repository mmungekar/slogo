package back_end.commands.presetLibrary;

import java.util.List;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class Turtles extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>, Constant{

	@Override
	public double Execute(Model model) {
		return model.getTurtleCount();
	}
}
