package back_end.commands.presetLibrary;

import java.util.List;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;

public class Product extends SimpleParameterCommand implements CommandInterface<ExpressionTree>, Constant{



	@Override
	public double Execute(Model state) {
		List<Double> parameters = this.getParameterValue();
		double product = 1;
		for(Double a : parameters){
			product *= a;
		}
		return product;
	}

}
