package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class IsPenDown extends NoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		double down = model.isPenDown(0) == true ? 1 : 0;
		return down;
	}

}
