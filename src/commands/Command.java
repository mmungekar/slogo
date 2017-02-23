package commands;

import back_end.ModelState;

public interface Command
{
	void setParameters(String nextLine);
	void execute(ModelState state);
}
