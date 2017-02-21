package back_end;


import java.util.Scanner;
import commands.Command;

public class StringInterpreter
{

	private CommandLibrary commandLib;
	Scanner scanner;

	public StringInterpreter()
	{
		commandLib = new CommandLibrary();
	}

	public Command interpret(String commandString)
	{
		scanner = new Scanner(commandString);

		Command command = commandLib.getCommand(scanner.next());
		command.setParameters(scanner.nextLine());

		return command;
	}
}
