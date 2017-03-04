package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;


public class Home extends SimpleParameterCommand implements CommandInterface<ExpressionTree>{

	@Override
	public double Execute(Model model) {
		double dx = model.getHome().getX() - model.getX(0);
		double dy = model.getHome().getY() - model.getY(0);
		double distance = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)) , 1/2d);
		model.sendTurtleHome(0);
		return distance;
	}
	

}
