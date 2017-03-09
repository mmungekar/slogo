package back_end.commands.commandLibrary.movement;

import back_end.model.scene.Model;
import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;

public class SetHeading extends SimpleParameterCommand implements CommandInterface{
	@Override
	public double Execute(Model model) {

		Double returnVal = (double) 0;
		for(Double a: getParameters()){
			returnVal += model.operateOnTurtle(turtle -> turtle.setAngle(-1 * a));
		}
		return returnVal;
	}

}
