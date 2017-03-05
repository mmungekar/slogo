package back_end.commands.commandLibrary.movement;

import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class LeftRight extends PresetCommand implements CommandInterface{
	protected void rotate(Model model, int TurtleID, double angle){
	    model.setAngle(TurtleID, model.getAngle(0) + angle);
	}
}
