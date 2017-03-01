package back_end.commands;

import back_end.Model;
import back_end.NotEnoughParameterException;

public class Home implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		double dx = model.getHome().getX() - model.getX(0);
		double dy = model.getHome().getY() - model.getY(0);
		double distance = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)) , 1/2d);
		model.sendTurtleHome(0);
		return distance;
	}
	

}
