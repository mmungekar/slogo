package back_end.commands.presetLibrary;

import back_end.commands.abstracts.PresetCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class ArcTangent extends PresetCommand implements CommandInterface<Oxygen<Double>>, Constant{

	@Override
	public double Execute(Model state) {
		double a = this.getParameterValue()[0];
		return Math.toDegrees(Math.atan(a));
	}


}
