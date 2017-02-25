package commands;

import back_end.Model;

public interface CommandInterface
{
	void setParameters(String nextLine);
	void Execute(Model state);
	int getParameterCount();
}
