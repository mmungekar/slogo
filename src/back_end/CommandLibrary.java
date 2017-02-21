package back_end;

import java.io.IOException;
import java.util.Properties;

import commands.Command;

public class CommandLibrary
{
	private Properties langProps;
	
	public CommandLibrary()
	{
		langProps = new Properties();
		try
		{
			langProps.load(getClass().getClassLoader().getResourceAsStream("English.properties"));
		}
		catch (IOException e1)
		{
			throw new Error("error in English.properties file format");
		}
	}

	public Command getCommand(String firstWord)
	{
		return createCommand(langProps.getProperty(firstWord));
	}

	private Command createCommand(String property)
	{
		if (property.equals("Forward"))
		{
			return new MovementCommand();
		}
		else throw new Error("Function not defined");
	}
}
