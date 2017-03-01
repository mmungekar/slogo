package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class PenDown extends NoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		model.setPenDown(0);
		return 1;
	}
	

}
