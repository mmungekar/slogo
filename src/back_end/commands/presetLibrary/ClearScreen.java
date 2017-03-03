package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.PresetCommand;
import back_end.interfaces.CommandInterface;

public class ClearScreen extends PresetCommand implements CommandInterface<ExpressionTree>{

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
