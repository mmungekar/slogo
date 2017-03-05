package back_end.commands.presetLibrary;

import java.util.List;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;

public class Equal extends SimpleParameterCommand implements CommandInterface<ExpressionTree>, Constant{
	/**
	 * If all equal, return 1; if not all equal, return 0
	 */
	@Override
	public double Execute(Model state) { 
		List<Double> parameters = this.getParameterValue();
		for(Double a:parameters){
			if(a!=parameters.get(0)){
				return 0;
			}
		}
		return 1;
	}
}
