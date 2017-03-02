package back_end.libraries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import back_end.interfaces.CommandInterface;
import back_end.model.ProgramParser;
import back_end.commands.CustomCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.InitializationException;
import back_end.exceptions.UnrecognizedCommandException;

public class CommandLibrary {
	private ResourceBundle resource;
	private ResourceBundle presource;
	private ArrayList<String> commandNames;
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";
	public static final String PARAMETER_DIRECTORY = "resources/parameters/";
	public static final String COMMAND_PREFIX = "back_end.commands.presetLibrary.";
	private ProgramParser mParser;
	private HashMap<String, CustomCommand> customCommands;
	
	private String currentLanguage;	
	

	public CommandLibrary(String language) {
		this.currentLanguage = language;
		this.buildLib();
	}

	public void buildLib() {
		commandNames = new ArrayList<String>();
		customCommands = new HashMap<String, CustomCommand>();
		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + this.currentLanguage);
		presource = ResourceBundle.getBundle(PARAMETER_DIRECTORY + "Parameter");
		mParser = new ProgramParser();
		mParser.addPatterns(LANGUAGE_DIRECTORY + this.currentLanguage);
		for (String x : resource.keySet()) {
			commandNames.add(x);
		}
		customCommands = new HashMap<String, CustomCommand>();
	}

	public CommandInterface getCommand(String command) throws CommandException {
		try {
			String official = mParser.getSymbol(command);
			Class<?> clazz = Class.forName(COMMAND_PREFIX.concat(official));
			return (CommandInterface) clazz.newInstance();
		} catch (ClassNotFoundException ex) {
			if (customCommands.containsKey(command)) return customCommands.get(command);
			else throw new UnrecognizedCommandException(command);
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InitializationException(command);
		}
	}
	
	public HashMap<String, CustomCommand> getCustomCommandContainer()
	{
		return customCommands;
	}
	
	public int getNumParam(String command) throws UnrecognizedCommandException {

		String official = mParser.getSymbol(command);
		return Integer.parseInt(presource.getString(official));
	}
}