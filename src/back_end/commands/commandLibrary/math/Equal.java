package back_end.commands.commandLibrary.math;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Equal extends SimpleParameterCommand implements CommandInterface{

	/**
	 * If all equal, return 1; if not all equal, return 0
	 */
	@Override
	public double Execute(Model state) { 
		for(Double a:getParameterValue()){
			if(a!=getParameterValue().get(0)){
				return 0;
			}
		}
		return 1;
	}
}
