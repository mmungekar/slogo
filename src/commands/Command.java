package commands;

<<<<<<< HEAD
import java.util.Scanner;

public interface Command {

	void setParameters(Scanner scanner, String type);

=======
public interface Command
{
	void setParameters(String nextLine);
	ParameterContainer getParameters();
>>>>>>> 9d805158890fce6e9879a16cf63a64d664c6ee00
}
