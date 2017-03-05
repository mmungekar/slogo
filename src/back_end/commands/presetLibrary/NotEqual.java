package back_end.commands.presetLibrary;

import java.util.List;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;


public class NotEqual extends SimpleParameterCommand implements CommandInterface<ExpressionTree>, Constant{
/**
 * If not all equal, return 1
 */
	@Override
	public double Execute(Model state) { 
		List<Double> parameters = this.getParameterValue();
		for(Double a:parameters){
			if(a!=parameters.get(0)){
				return 1;
			}
		}
		return 0;
	}
}


