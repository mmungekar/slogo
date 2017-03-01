package back_end.commands;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commandAbstracts.ForwardBackward;

public class Backward extends ForwardBackward implements CommandInterface {
    

	@Override
	public double Execute(Model model) {
		this.sendToNewPos(model, 0, -1 * this.getParameterValue());
		return this.getParameterValue();
	}

}

