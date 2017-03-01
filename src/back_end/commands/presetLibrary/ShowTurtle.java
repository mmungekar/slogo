package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class ShowTurtle extends NoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		model.setVisible(0);
		return 1;
	}
	

}
