package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;


public class IsShowing extends SimpleParameterCommand implements CommandInterface<ExpressionTree>{


	@Override
	public double Execute(Model model) {
		return model.isVisible(0) == true ? 1 : 0;
	}

}
