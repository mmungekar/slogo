package back_end.commands.presetLibrary;

import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.PresetCommand;
import back_end.interfaces.CommandInterface;

public class HideTurtle extends PresetCommand implements CommandInterface<Oxygen<Double>>{
	
	@Override
	public double Execute(Model model) {
		model.setInVisible(0);
		return 0;
	}
	

}
