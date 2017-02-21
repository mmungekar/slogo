package back_end;

import java.util.Scanner;

import commands.Command;

public class StringInterpreter
{
	private CommandLibrary commandBuilder;
	Scanner scanner;
	
	public StringInterpreter()
	{
		commandBuilder = new CommandLibrary();
	}
	
	public Command interpret(String commandString)
	{
		scanner = new Scanner(commandString);
		Command command = commandBuilder.getCommand(scanner.next());
		
		command.setParameters(scanner.nextLine());
		return command;
	}
}
