package back_end.commands.presetLibrary;

import back_end.commands.abstracts.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.model.Oxygen;

public class SetTowards extends PresetCommand implements CommandInterface<ExpressionTree>{

	@Override
	public double Execute(Model model) {
		double ox = this.getParameterValue()[0];
		double oy = this.getParameterValue()[1];
		
	    double dx = (model.getHome().getX() + ox) - model.getX(0);
	    double dy = (model.getHome().getY() - oy) - model.getY(0);
	    
	    double prevAngle = model.getAngle(0);
	    double angle = Math.toDegrees(Math.atan(dy / dx));
	    model.setAngle(0, angle);
	    return angle-prevAngle;
	}

}
