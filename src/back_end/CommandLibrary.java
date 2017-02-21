package back_end;

import java.util.ArrayList;
import java.util.ResourceBundle;

import commands.Command;
import commands.ForwardCommand;

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
			String[] possibleNames = x.split("[|]");
			for (String y : possibleNames)
			{
				if (y.equals(firstWord)) return selectCommand(x);				
			}
		}
		throw new Error("Command not found");
	}

	private Command selectCommand(String x)
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

	private Command createCommand(String commandName)
	{
		if ("Forward Backward Left Right".contains(commandName)) return new ForwardCommand();
		
		throw new Error("library error");
	}
}
