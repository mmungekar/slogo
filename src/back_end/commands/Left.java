package back_end.commands;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commandAbstracts.LeftRight;

public class Left extends LeftRight implements CommandInterface{
	@Override
	public double Execute(Model model) {
		this.rotate(model, 0, -1 * this.getParameterValue());
	    return this.getParameterValue();
	}

}
