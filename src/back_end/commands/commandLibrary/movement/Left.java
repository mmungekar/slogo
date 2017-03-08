package back_end.commands.commandLibrary.movement;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Left extends LeftRight implements CommandInterface{
	@Override
	public double Execute(Model model) {
		Double returnVal = (double) 0;
		for(Double a: getParameterValue()){
		this.rotateRight(model, -1*a);
		returnVal = a;
		}
		return returnVal;
	}
}
