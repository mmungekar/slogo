package back_end.commands.queries;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commands.CommandInterface;

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
