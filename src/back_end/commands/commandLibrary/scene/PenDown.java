package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class PenDown extends PresetCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		model.setPenDown(0);
		return 1;
	}
	

}
