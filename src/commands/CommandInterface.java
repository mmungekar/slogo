package commands;

import back_end.Input;
import back_end.ModelState;

public interface CommandInterface
{
	void setParameters(Input... input);
	double Execute(ModelState state);
	int getParameterCount();
}
