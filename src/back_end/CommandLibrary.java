package back_end;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.ResourceBundle;
import commands.CommandInterface;
import commands.ForwardCommand;
import commands.RotationCommand;
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
	// public String getStandardCommandName(String command) {
	// command = command.toLowerCase();
	// for (String x : commandNames) {
	// String[] possibleNames = resource.getString(x).split("[|]");
	// for (String y : possibleNames) {
	// if (y.equals(command))
	// return x;
	// }
	// }
	// throw new Error("Command not found");
	// }
	public CommandInterface getCommand(String command)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO: change into more robust way of instantiating the class
		String official = mParser.getSymbol(command);
		Class<?> clazz = Class.forName(COMMAND_PREFIX.concat((official)));
		return (CommandInterface) clazz.newInstance();
	}
	public int getNumParam(String command) {
		String official = mParser.getSymbol(command);
		return Integer.parseInt(presource.getString(official));
	}
}