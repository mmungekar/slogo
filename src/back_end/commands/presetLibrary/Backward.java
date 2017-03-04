package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.commands.abstracts.ForwardBackward;
import back_end.interfaces.CommandInterface;

public class Backward extends ForwardBackward implements CommandInterface<ExpressionTree> {

	@Override
	public double Execute(Model model) {
		double a = this.getParameterValue().get(0);
		this.sendToNewPos(model, 0, -1 * a);
		return a;
	}

}
