package back_end.commands.commandLibrary.movement;

import back_end.commands.commandLibrary.OneParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class LeftRight extends OneParameterCommand implements CommandInterface{
	protected void rotateRight(Model model, double angle){
	    model.rotate(angle);
	}
}
