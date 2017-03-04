package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.commands.abstracts.LeftRight;
import back_end.interfaces.CommandInterface;

public class Right extends LeftRight implements CommandInterface<ExpressionTree>{

	@Override
	public double Execute(Model model) {
		double a = this.getParameterValue().get(0);
		this.rotate(model, 0, a);
	    return a;
	}

}
