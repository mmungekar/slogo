package commands;

import back_end.Input;
import back_end.ModelState;

public interface CommandInterface
{
	void setParameters(double...ds);
	double Execute(ModelState state);
	int getParameterCount();
}
