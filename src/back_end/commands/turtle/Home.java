package back_end.commands.turtle;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commands.CommandInterface;

public class Home implements CommandInterface{

	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		double ox = model.getX(0);
		double oy = model.getY(0);
		double distance = Math.pow((Math.pow(ox, 2) + Math.pow(oy, 2)) , 1/2d);
		model.sendTurtleHome(0);
		return distance;
	}
	

}
