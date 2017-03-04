package back_end.commands.abstracts;

import back_end.model.Model;

public abstract class LeftRight extends SimpleParameterCommand{
	protected void rotate(Model model, int TurtleID, double angle){
	    model.setAngle(TurtleID, model.getAngle(0) + angle);
	}
}
