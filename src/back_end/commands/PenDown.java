package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

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
