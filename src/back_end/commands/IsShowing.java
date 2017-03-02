package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class IsShowing implements CommandInterface<Double>{

	@Override
	public void setParameters(Double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		double visible = model.isVisible(0) == true ? 1 : 0;
		return visible;
	}

}
