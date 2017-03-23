package back_end.commands.commandLibrary.turtle;

import back_end.model.scene.Model;
import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;


public class YCoordinate extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.getTurtleMaster().getCoordinate(1);
	}

}
