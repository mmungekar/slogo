package back_end.commands.commandLibrary.math;

import java.util.List;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class And extends SimpleParameterCommand implements CommandInterface{
	@Override
	public double Execute(Model state) { 
		for(Double a:getParameterValue()){
			if(a==0){
				return 0;
			}
		}
		return 1;
	}
}