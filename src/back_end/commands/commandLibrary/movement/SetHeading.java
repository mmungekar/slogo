package back_end.commands.commandLibrary.movement;

import back_end.model.scene.Model;
import back_end.commands.commandLibrary.OneParameterCommand;
import back_end.interfaces.CommandInterface;

public class SetHeading extends OneParameterCommand implements CommandInterface{
	@Override
	public double Execute(Model model) {
		this.getParams();
		model.operateOnTurtle(turtle -> turtle.setAngle(A));
	    return A;
	}

}
