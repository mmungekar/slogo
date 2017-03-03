package back_end.commands.presetLibrary;

import back_end.model.Model;
import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.Oxygen;

public class XCoordinate extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>{

	@Override
	public double Execute(Model model) {
		return model.getCoordinate(0);
	}

}
