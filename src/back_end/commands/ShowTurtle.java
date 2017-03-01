package back_end.commands;

import back_end.Model;
import back_end.NotEnoughParameterException;

public class ShowTurtle implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		model.setVisible(0);
		return 1;
	}
	

}
