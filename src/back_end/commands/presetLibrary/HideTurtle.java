package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class HideTurtle extends NoParameterCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		model.setInVisible(0);
		return 0;
	}
	

}
