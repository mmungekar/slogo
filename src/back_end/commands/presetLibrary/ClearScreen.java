package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.NoParameterCommand;

public class ClearScreen extends NoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		double ox = model.getX(0);
		double oy = model.getY(0);
		double distance = Math.pow((Math.pow(ox, 2) + Math.pow(oy, 2)) , 1/2d);
		model.sendTurtleHome(0);
		model.setClear(true);
		return distance;
	}
	

}
