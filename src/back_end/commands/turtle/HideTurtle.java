package back_end.commands.turtle;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commands.CommandInterface;

public class HideTurtle implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		model.setInVisible(0);
		return 0;
	}
	

}
