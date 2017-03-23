package back_end.commands.commandLibrary.math;


import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Not extends SimpleParameterCommand implements CommandInterface{
/**
 * If any are not equal to zero, return zero
 */
	@Override
	public double Execute(Model state) { 
		for(Double a:getParameters()){
			if(a!=0){
				return 0;
			}
		}
		return 1;
	}

}
