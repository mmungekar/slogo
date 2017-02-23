package back_end;

import java.util.ArrayList;
import java.util.ResourceBundle;

import commands.CommandInterface;
import commands.ForwardCommand;
import commands.RotationCommand;

public class CommandLibrary
{
	private ResourceBundle resource;
	private ArrayList<String> commandNames;
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";
	
	public CommandLibrary()
	{
		buildLib("English");
	}

	public void buildLib(String language)
	{
		commandNames = new ArrayList<String>();
		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + language);
		
		for (String x : resource.keySet())
		{
			commandNames.add(resource.getString(x));
		}
	}

	public CommandInterface getCommand(String firstWord)
	{
		firstWord = firstWord.toLowerCase();
		for (String x : commandNames)
		{
			String[] possibleNames = x.split("[|]");
			for (String y : possibleNames)
			{
				if (y.equals(firstWord)) return selectCommand(x);				
			}
		}
		throw new Error("Command not found");
	}

	private CommandInterface selectCommand(String x)
	{
			for (String z : resource.keySet())
			{
				if (x.equals(resource.getString(z)))
				{
					return createCommand(z);
				}
			}
		throw new Error("back end error");
	}

	private CommandInterface createCommand(String commandName)
	{
		if ("Forward Backward".contains(commandName)) return new ForwardCommand("Forward".contains(commandName));
		
		if ("Left Right".contains(commandName)) return new RotationCommand("Right".contains(commandName));
		
		throw new Error("library error");
	}
}
