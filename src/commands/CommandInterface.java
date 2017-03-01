package commands;

import back_end.Model;
import back_end.NotEnoughParameterException;

public interface CommandInterface {
	void setParameters(double... ds) throws NotEnoughParameterException;

	double Execute(Model model);
}
