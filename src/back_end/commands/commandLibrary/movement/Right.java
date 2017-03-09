package back_end.commands.commandLibrary.movement;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Right extends LeftRight implements CommandInterface{
	@Override
	public double Execute(Model model) {
		Double returnVal = (double) 0;
		for(Double a: getParameters()){
		this.rotateRight(model,a);
		returnVal = a;
		}
		return returnVal;
	}
}
