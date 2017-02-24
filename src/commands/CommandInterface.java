package commands;

import back_end.ModelState;

public interface CommandInterface
{
	void setParameters(String nextLine);
	void Execute(ModelState state);
	int getParameterCount();
}
