package back_end.commands.commandLibrary.scene;

import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;
import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;

public class ClearScreen extends PresetCommand implements CommandInterface{

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
