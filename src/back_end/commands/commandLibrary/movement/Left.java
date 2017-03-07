package back_end.commands.commandLibrary.movement;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Left extends LeftRight implements CommandInterface{
	@Override
	public double Execute(Model model) {
		this.getParams();
		this.rotateRight(model, -1 * A);
	    return A;
	}

}
