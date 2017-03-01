package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class Home extends NoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		double dx = model.getHome().getX() - model.getX(0);
		double dy = model.getHome().getY() - model.getY(0);
		double distance = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)) , 1/2d);
		model.sendTurtleHome(0);
		return distance;
	}
	

}
