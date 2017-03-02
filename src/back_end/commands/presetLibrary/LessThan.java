package back_end.commands.presetLibrary;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class LessThan extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>, Constant{

	@Override
	public double Execute(Model state) { 
		double a = this.getParameterValue().get(0);
		double b = this.getParameterValue().get(1);
		return a < b ? 1 : 0;
	}

}
