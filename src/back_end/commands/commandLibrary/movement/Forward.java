package back_end.commands.commandLibrary.movement;

import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;
import back_end.interfaces.CommandInterface;

public class Forward extends ForwardBackward {

	@Override
	public double Execute(Model model) {
		return this.sendToNewPos(model, 0, 1);
	}

}
