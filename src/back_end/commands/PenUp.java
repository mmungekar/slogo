package back_end.commands;

import back_end.Model;
import back_end.NotEnoughParameterException;
import commands.CommandInterface;

public class PenUp implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		model.setPenUp(0);
		return 0;
	}
	

}
