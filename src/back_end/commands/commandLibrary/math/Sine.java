package back_end.commands.commandLibrary.math;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Sine extends SimpleParameterCommand implements CommandInterface{


	@Override
	public double Execute(Model state) {
		return Math.sin(Math.toRadians(getParameterValue().get(0)));
	}

}
