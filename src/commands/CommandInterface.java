package commands;

import back_end.Model;

public interface CommandInterface {
	void setParameters(double... ds) throws ParameterException;

	double Execute(Model model);

	int getParameterCount();
}
