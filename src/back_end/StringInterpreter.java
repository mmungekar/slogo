package back_end;


import java.util.Scanner;

import commands.Command;
import java.util.ResourceBundle;

public class StringInterpreter
{
	
	private CommandLibrary commandBuilder;
	Scanner scanner;
	
	public StringInterpreter()
	{
		commandBuilder = new CommandLibrary();
	}
	
	public void interpret(String commandString)
	{
		System.out.println("Text submitted: " + commandString);

		scanner = new Scanner(commandString);
		String type = scanner.next();
		//NEEDS TO BE ALTERED IF REPETITION
		Command command = commandBuilder.getCommand(type);
		command.setParameters(scanner, type);

	}
	

	// by miguel, can be changed
	private String currentLanguage;
	private ResourceBundle resources;

	public void setLanguage(String language) {
		this.currentLanguage = language;
		//resources = ResourceBundle.getBundle("resources/languages/" + currentLanguage);
		System.out.println("Language Changed To : " + language);

	}


}
