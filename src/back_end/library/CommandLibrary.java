package back_end;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.ResourceBundle;
import commands.CommandInterface;

public class CommandLibrary {
	private String language = "English";
	private ResourceBundle resource;
	private ResourceBundle presource;
	private ArrayList<String> commandNames;
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";
	public static final String PARAMETER_DIRECTORY = "resources/parameters/";
	public static final String COMMAND_PREFIX = "back_end.commands.";
	private ProgramParser mParser;

	public CommandLibrary() {
		buildLib(this.language);
	}

	public void buildLib(String language) {
		commandNames = new ArrayList<String>();
		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + language);
		presource = ResourceBundle.getBundle(PARAMETER_DIRECTORY + "Parameter");
		mParser = new ProgramParser();
		mParser.addPatterns(LANGUAGE_DIRECTORY + language);
		for (String x : resource.keySet()) {
			commandNames.add(x);
		}
	}

	public CommandInterface getCommand(String command) throws UnrecognizedCommandException {
		try {
			String official = mParser.getSymbol(command);
			Class<?> clazz = Class.forName(COMMAND_PREFIX.concat((official)));
			return (CommandInterface) clazz.newInstance();
		} catch (ClassNotFoundException ex) {
			throw new UnrecognizedCommandException("Cannot find command: " + command);
		} catch (InstantiationException ex) {
			throw new UnrecognizedCommandException("Cannot initialize command: " + command);
		} catch (IllegalAccessException ex) {
			throw new UnrecognizedCommandException("Cannot initialize command: " + command);
		}
	}

	public int getNumParam(String command) throws UnrecognizedCommandException {

		String official = mParser.getSymbol(command);
		return Integer.parseInt(presource.getString(official));
	}
}