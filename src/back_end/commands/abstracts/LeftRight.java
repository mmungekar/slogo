package back_end.commands.abstracts;

import back_end.model.Model;

public abstract class LeftRight extends SimpleParameterCommand{
	protected void rotate(Model model, double angle){
	    model.setAngle(model.getAngle(0) + angle);
	}
}
