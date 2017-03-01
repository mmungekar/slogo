package back_end.commands;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commandAbstracts.LeftRight;

public class Right extends LeftRight implements CommandInterface{

	@Override
	public double Execute(Model model) {
		this.rotate(model, 0, this.getParameterValue());
	    return this.getParameterValue();
	}

}
