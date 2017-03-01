package back_end.commands;

import back_end.Model;
import back_end.NotEnoughParameterException;
import commands.CommandInterface;

public class IsShowing implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		double visible = model.isVisible(0) == true ? 1 : 0;
		return visible;
	}

}
