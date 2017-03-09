package back_end.commands.commandLibrary.movement;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class ForwardBackward extends SimpleParameterCommand implements CommandInterface{
	
	protected void moveForward(Model model,double mag){
		model.operateOnTurtle(turtle -> turtle.moveForward(mag));
		
	}
}