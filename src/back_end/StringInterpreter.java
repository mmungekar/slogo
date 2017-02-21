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
		System.out.println("Text submitted: " + commandString);

		scanner = new Scanner(commandString);
		//NEEDS TO BE ALTERED IF REPETITION
		Command command = commandLib.getCommand(scanner.next());
		command.setParameters(scanner.nextLine());
		return command;
	}
	
}
