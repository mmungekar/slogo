package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class PenUp extends NoParameterCommand implements CommandInterface {
	@Override
	public double Execute(Model model) {
		model.setPenUp(0);
		return 0;
	}

}
