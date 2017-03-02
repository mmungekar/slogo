package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class ClearScreen implements CommandInterface<Double>{

	@Override
	public void setParameters(Double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		double ox = model.getX(0);
		double oy = model.getY(0);
		double distance = Math.pow((Math.pow(ox, 2) + Math.pow(oy, 2)) , 1/2d);
		model.sendTurtleHome(0);
		model.setClear(true);
		return distance;
	}
	

}
