package back_end.commands.commandLibrary.movement;

import back_end.model.scene.Model;
import back_end.interfaces.CommandInterface;

public class Backward extends ForwardBackward implements CommandInterface {
	@Override
	public double Execute(Model model) {
		Double returnVal = (double) 0;
		for(Double a: getParameters()){
		this.moveForward(model, -1*a);
		returnVal += a;
		}
		return returnVal;
	}
}
