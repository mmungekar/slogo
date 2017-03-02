package back_end.commands.presetLibrary;

import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;

public class Home extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>{

	@Override
	public double Execute(Model model) {
		// FIXME Turtle ID issue
		double dx = model.getHome().getX() - model.getX(1);
		double dy = model.getHome().getY() - model.getY(1);
		double distance = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)) , 1/2d);
		model.sendTurtleHome();
		return distance;
	}
	

}
