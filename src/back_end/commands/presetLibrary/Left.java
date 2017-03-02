package back_end.commands.presetLibrary;

import back_end.model.Model;
import back_end.commands.abstracts.LeftRight;
import back_end.interfaces.CommandInterface;
import back_end.model.Oxygen;

public class Left extends LeftRight implements CommandInterface<Oxygen<Double>>{
	@Override
	public double Execute(Model model) {
		double a = this.getParameterValue()[0];
		this.rotate(model, 0, -1 * a);
	    return a;
	}

}
