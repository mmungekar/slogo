package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;


public class XCoordinate extends SimpleParameterCommand implements CommandInterface<ExpressionTree>{


	@Override
	public double Execute(Model model) {
		return model.getHome().getX() + model.getX(0);
	}

}
