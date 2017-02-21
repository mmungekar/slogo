package commands;

public interface Command
{
	void setParameters(String nextLine);
	ParameterContainer getParameters();
}
