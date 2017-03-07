package back_end.commands.commandLibrary.movement;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Forward extends ForwardBackward implements CommandInterface{

	@Override
	public double Execute(Model model) {
		this.getParams();
		this.moveForward(model, A);
		return A;
	}

}
