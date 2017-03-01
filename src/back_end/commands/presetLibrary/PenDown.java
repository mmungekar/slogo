package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.exceptions.NotEnoughParameterException;

public class PenDown implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		model.setPenDown(0);
		return 1;
	}
	

}
