package back_end.commands.commandLibrary.movement;


import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class SetPosition extends PresetCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		double x = this.getParameterValue()[0];
		double y = this.getParameterValue()[1];
		double ox = model.getX(0);
		double oy = model.getY(0);
		double distance = Math.pow((Math.pow((ox-x), 2) + Math.pow((oy-y), 2)) , 1/2d);
		
		model.setPos(0, model.getHome().getX() + x, model.getHome().getY() - y);
		return distance;
	}

}
