package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.exceptions.NotEnoughParameterException;

public interface CommandInterface {
	void setParameters(double... ds) throws NotEnoughParameterException;

	double Execute(Model model);
}
