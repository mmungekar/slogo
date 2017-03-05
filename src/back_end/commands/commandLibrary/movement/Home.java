package back_end.commands.commandLibrary.movement;

import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;
import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;

public class Home extends PresetCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		double dx = model.getHome().getX() - model.getX(0);
		double dy = model.getHome().getY() - model.getY(0);
		double distance = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)) , 1/2d);
		model.sendTurtleHome(0);
		return distance;
	}
	

}
