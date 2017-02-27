package commands;

import back_end.Model;

public interface CommandInterface
{
	void setParameters(String nextLine) throws ParameterException;
	void Execute(Model model);
	int getParameterCount();
}
