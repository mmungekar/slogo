package back_end.commands.commandLibrary.movement;

import back_end.model.scene.Model;
import back_end.interfaces.CommandInterface;

public class Backward extends ForwardBackward implements CommandInterface {

	@Override
	public double Execute(Model model) {
		this.getParams();
		this.moveForward(model, -1 * A);
		return A;
	}


}
