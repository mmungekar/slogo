package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.NoInputCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class YCoordinate extends NoInputCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.getCoordinate(1);
	}

}
