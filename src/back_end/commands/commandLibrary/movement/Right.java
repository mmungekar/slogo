package back_end.commands.commandLibrary.movement;

import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class Right extends LeftRight{

	@Override
	public double Execute(Model model) {
		double a = this.getParameterValue()[0];
		this.rotate(model, 0, a);
	    return a;
	}

}
