package back_end.commands.commandLibrary.movement;

import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class ForwardBackward extends PresetCommand  implements CommandInterface{
	protected double sendToNewPos(Model model, int TurtleID, int direct){
		double mag = this.getParameterValue()[0];
		double angle = model.getAngle(0);
		double dx = Math.cos(Math.toRadians(angle)) * mag * direct;
		double dy = Math.sin(Math.toRadians(angle)) * mag * direct;
		double x = model.getX(0);
		double y = model.getY(0);
		model.setPos(TurtleID, x + dx, y + dy);
		return mag;
	}
}
