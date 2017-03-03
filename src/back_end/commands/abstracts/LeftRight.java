package back_end.commands.abstracts;

import back_end.model.Model;

public abstract class LeftRight extends SimpleParameterCommand{
	protected void rotateRight(Model model, double angle){
	    model.rotate(angle);
	}
}
