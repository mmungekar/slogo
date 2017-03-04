package back_end.commands.presetLibrary;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;

public class Not extends SimpleParameterCommand implements CommandInterface<ExpressionTree>, Constant{

	@Override
	public double Execute(Model state) { 
		double a = this.getParameterValue().get(0);
		return a == 0 ? 1 : 0;
	}

}
