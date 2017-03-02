package back_end.commands;


import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class ShowTurtle implements CommandInterface<Double>{

	@Override
	public void setParameters(Double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		model.setVisible(0);
		return 1;
	}
	

}
