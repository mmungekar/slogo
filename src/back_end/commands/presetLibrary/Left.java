package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.LeftRight;
import back_end.exceptions.NotEnoughParameterException;

public class Left extends LeftRight implements CommandInterface{
	@Override
	public double Execute(Model model) {
		this.rotate(model, 0, -1 * this.getParameterValue());
	    return this.getParameterValue();
	}

}
