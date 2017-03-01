package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class XCoordinate extends NoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.getHome().getX() + model.getX(0);
	}

}
