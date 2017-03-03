package back_end.commands.presetLibrary;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class SetTowards extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>{

	@Override
	public double Execute(Model model) {
		double ox = this.getParameterValue().get(0);
		double oy = this.getParameterValue().get(1);
		
		/*
	    double dx = (model.getHome().getX() + ox) - model.getX(1);
	    double dy = (model.getHome().getY() - oy) - model.getY(1);
	    double prevAngle = model.getAngle(1);
	    
	    double angle = Math.toDegrees(Math.atan(dy / dx));
	    model.setAngle(angle);
	    //return angle-prevAngle;
	    */
	    return model.setTowards(ox, oy);
	}

}
