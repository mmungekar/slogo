package back_end.commands.presetLibrary;

import java.util.List;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class Sum extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>, Constant{

	@Override
	public double Execute(Model state) {
		List<Double> parameters = this.getParameterValue();
		double sum = 0;
		for(Double a : parameters){
			sum += a;
		}
		return sum;
	}
}
