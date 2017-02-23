package commands;

import back_end.ModelState;

public interface Command
{
	void setParameters(String nextLine);
	void Execute(ModelState state);
	int getParameterCount();
}
