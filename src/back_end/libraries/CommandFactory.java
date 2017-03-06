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
	public static final String PARAMETER_DIRECTORY = "resources/parameters/";
	public static final String COMMAND_PREFIX = "resources.commands.commandpath";
	private ResourceBundle COMMAND_PATHS = ResourceBundle.getBundle(COMMAND_PREFIX);
	private ProgramParser mParser;
	private String currentLanguage;	
	

	public CommandFactory(String language) {
		this.currentLanguage = language;
		this.buildLib();
	}

	public void buildLib() {
		commandNames = new ArrayList<String>();

		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + this.currentLanguage);
		presource = ResourceBundle.getBundle(PARAMETER_DIRECTORY + "Parameter");
		mParser = new ProgramParser();
		mParser.addPatterns(LANGUAGE_DIRECTORY + this.currentLanguage);
		for (String x : resource.keySet()) {
			commandNames.add(x);
		}
	}

	public CommandInterface getCommand(String command) throws CommandException {
		try {
			String official = mParser.getSymbol(command);
			Class<?> clazz = Class.forName(COMMAND_PATHS.getString(official));
			return (CommandInterface) clazz.newInstance();
		} catch (ClassNotFoundException ex) {
			 throw new UnrecognizedCommandException(command);
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InitializationException(command);
		}
	}
	
	public int getNumParam(String command) throws UnrecognizedCommandException {

		String official = mParser.getSymbol(command);
		return Integer.parseInt(presource.getString(official));
	}
}