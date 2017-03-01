package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;


public class YCoordinate extends NoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.getHome().getY() - model.getY(0);
	}

}
