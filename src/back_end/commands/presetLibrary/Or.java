package back_end.commands.presetLibrary;

import java.util.List;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;

public class Or extends SimpleParameterCommand implements CommandInterface<ExpressionTree>, Constant{

	@Override
	public double Execute(Model state) { 
		List<Double> parameters = this.getParameterValue();
		for(Double a:parameters){
			if(a!=0){
				return 1;
			}
		}
		return 0;
	}
}



