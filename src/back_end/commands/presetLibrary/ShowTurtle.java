package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.PresetCommand;
import back_end.interfaces.CommandInterface;

public class ShowTurtle extends PresetCommand implements CommandInterface<ExpressionTree>{

	@Override
	public double Execute(Model model) {
		model.setVisible(0);
		return 1;
	}
	

}
