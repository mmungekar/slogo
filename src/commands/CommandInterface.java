package commands;

import back_end.ModelState;

public interface CommandInterface
{
	void setParameters(String nextLine) throws ParameterException;
	void Execute(ModelState state);
	int getParameterCount();
}
