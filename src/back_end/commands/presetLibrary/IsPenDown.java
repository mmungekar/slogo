package back_end.commands.presetLibrary;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;


public class IsPenDown extends SimpleParameterCommand implements CommandInterface<ExpressionTree>{


	@Override
	public double Execute(Model model) {
		double down = model.isPenDown(0) == true ? 1 : 0;
		return down;
	}

}
