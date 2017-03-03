package back_end.commands.abstracts;

import back_end.model.Model;

public abstract class ForwardBackward extends SimpleParameterCommand{
	protected void moveForward(Model model,double mag){
		model.moveForward(mag);
		
	}
}
