package back_end.commandAbstracts;

import back_end.Model;

public abstract class LeftRight extends SingleParameterCommand{
	protected void rotate(Model model, int TurtleID, double angle){
	    model.setAngle(TurtleID, model.getAngle(0) + angle);
	}
}
