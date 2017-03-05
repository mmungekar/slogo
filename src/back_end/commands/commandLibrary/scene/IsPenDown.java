package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class IsPenDown extends PresetCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		double down = model.isPenDown(0) == true ? 1 : 0;
		return down;
	}

}
