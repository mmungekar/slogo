package back_end.libraries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import back_end.interfaces.CommandInterface;
import back_end.overhead.ProgramParser;
import back_end.commands.custom.CustomCommand;

import back_end.exceptions.CommandException;
import back_end.exceptions.InitializationException;
import back_end.exceptions.UnrecognizedCommandException;

public class CommandFactory {
	private ResourceBundle resource;
	private ResourceBundle presource;
	private ArrayList<String> commandNames;
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";
	public static final String COMMAND_PREFIX = "resources.commands.commandpath";
	public static final String PARAMETER_DIRECTORY = "resources/parameters/";
	private ResourceBundle COMMAND_PATHS = ResourceBundle.getBundle(COMMAND_PREFIX);
	private ProgramParser mParser;
	private String currentLanguage;

	public CommandFactory(String language) {
		this.currentLanguage = language;
		this.buildLib();
	}

	public void buildLib() {
		commandNames = new ArrayList<String>();
		presource = ResourceBundle.getBundle(PARAMETER_DIRECTORY + "Parameter");
		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + this.currentLanguage);
		mParser = new ProgramParser();
		mParser.addPatterns(LANGUAGE_DIRECTORY + this.currentLanguage);
		for (String x : resource.keySet()) {
			commandNames.add(x);
		}
	}

	public CommandInterface getCommand(String official) throws CommandException {
		try {
			if (COMMAND_PATHS.containsKey(official)) {
				Class<?> clazz = Class.forName(COMMAND_PATHS.getString(official));
				return (CommandInterface) clazz.newInstance();
			} else
				throw new ClassNotFoundException(official);
		} catch (ClassNotFoundException ex) {
			throw new UnrecognizedCommandException(official);
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InitializationException(official);
		}
	}

	public int getNumParam(String command) throws UnrecognizedCommandException {

		String official = mParser.getSymbol(command);
		return Integer.parseInt(presource.getString(official));
	}
}