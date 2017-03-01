package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.SingleParameterCommand;

public class SetHeading extends SingleParameterCommand implements CommandInterface{
	@Override
	public double Execute(Model model) {
	    model.setAngle(0, this.getParameterValue());
	    return this.getParameterValue();
	}

}
