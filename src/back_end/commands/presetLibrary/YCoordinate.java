package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.exceptions.NotEnoughParameterException;

public class YCoordinate implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		return model.getY(0);
	}

}
