package back_end;

import java.util.ArrayList;
import java.util.ResourceBundle;

import commands.Command;

public class CommandLibrary
{
	private ResourceBundle resource;
	private ArrayList<String> commandNames;
	
	public CommandLibrary()
	{
		buildLib("English");
	}

	public void buildLib(String language)
	{
		commandNames = new ArrayList<String>();
		resource = ResourceBundle.getBundle("resources/languages/" + language);
		
		for (String x : resource.keySet())
		{
			commandNames.add(resource.getString(x));
		}
	}

	public Command getCommand(String firstWord)
	{
		for (String x : commandNames)
		{
			if (x.contains(firstWord))
			{
				for (String y : resource.keySet())
				{
					if (x.equals(resource.getString(y))) return createCommand(y);
				}
			}
		}
		throw new Error("command not found");
	}

	private Command createCommand(String commandName)
	{
		if (commandName.equals("Forward")) return new MovementCommand();
		
		throw new Error("back end error");
	}
}
