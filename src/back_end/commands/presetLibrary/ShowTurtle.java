package back_end.commands.presetLibrary;

import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;

public class ShowTurtle extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>{

	@Override
	public double Execute(Model model) {
		model.setVisible(0);
		return 1;
	}
	

}
