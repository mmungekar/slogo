package back_end.commands.presetLibrary;

import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.abstracts.PresetCommand;
import back_end.interfaces.CommandInterface;

public class SetHeading extends PresetCommand implements CommandInterface<ExpressionTree>{
	@Override
	public double Execute(Model model) {
		double a = this.getParameterValue()[0];
	    model.setAngle(0, a);
	    return a;
	}

}
