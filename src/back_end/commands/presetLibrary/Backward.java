package back_end.commands.presetLibrary;

import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.ForwardBackward;
import back_end.interfaces.CommandInterface;

public class Backward extends ForwardBackward implements CommandInterface<Oxygen<Double>> {

	@Override
	public double Execute(Model model) {
		double a = this.getParameterValue().get(0);
		this.sendToNewPos(model, -1 * a);
		return a;
	}

}
