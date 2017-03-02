package back_end.commands.abstracts;

import back_end.model.Model;

public abstract class ForwardBackward extends PresetCommand{
	protected void sendToNewPos(Model model, int TurtleID, double mag){
		double angle = model.getAngle(0);
		double dx = Math.cos(Math.toRadians(angle)) * mag;
		double dy = Math.sin(Math.toRadians(angle)) * mag;
		double x = model.getX(0);
		double y = model.getY(0);
		model.setPos(TurtleID, x + dx, y + dy);
	}
}
