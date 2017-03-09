package back_end.commands.commandLibrary.math;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Or extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model state) { 
		for(Double a:getParameterValue()){
			if(a!=0){
				return 1;
			}
		}
		return 0;
	}

}
