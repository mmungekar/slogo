package back_end.commands.presetLibrary;

import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;

public class SetHeading extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>{
	@Override
	public double Execute(Model model) {
		double a = this.getParameterValue().get(0);
	    model.setAngle(0, a);
	    return a;
	}

}
