package back_end;

import java.util.Scanner;

import commands.Command;

public class StringInterpreter
{
	private CommandBuilder commandBuilder;
	Scanner scanner;
	
	public StringInterpreter()
	{
		commandBuilder = new CommandBuilder();
	}
	
	public Command interpret(String commandString)
	{
		scanner = new Scanner(commandString);
		Command command = commandBuilder.getCommand(scanner.next());
		
		command.setParameters(scanner.nextLine());
		return command;
	}
}
