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
		return parseFirstWord(commandString);
	}

	private Command parseFirstWord(String commandString)
	{
		scanner = new Scanner(commandString);
		//ugly if statements go "hidden away" here, part of internal API; to extend commands, add a new line here
		//example: if first word = fd command, return createNewFDCommand(), which uses the scanner to parse the next parameters,
		// and call an error if the parameters don't make sense, or returns a fdcommand object with the correct parameters
		return null;
	}

}
