package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.abstracts.ForwardBackward;
import back_end.exceptions.NotEnoughParameterException;

public class Forward extends ForwardBackward implements CommandInterface{

	@Override
	public double Execute(Model model) {
		this.sendToNewPos(model, 0, this.getParameterValue());
		return this.getParameterValue();
	}

}

