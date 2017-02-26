package back_end;

import java.util.ArrayList;
import java.util.ResourceBundle;

import commands.CommandInterface;
import commands.ForwardCommand;
import commands.RotationCommand;

public class CommandLibrary {
	private String language = "English";
	private ResourceBundle resource;
	private ArrayList<String> commandNames;
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";

	public CommandLibrary() {
		buildLib(this.language);
	}

	public void buildLib(String language) {
		commandNames = new ArrayList<String>();
		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + language);

		for (String x : resource.keySet()) {
			commandNames.add(resource.getString(x));
		}
	}

	public CommandInterface getCommand(String firstWord) throws UnrecognizedCommandException, LibraryLookUpException {
		firstWord = firstWord.toLowerCase();
		for (String x : commandNames) {
			String[] possibleNames = x.split("[|]");
			for (String y : possibleNames) {
				if (y.equals(firstWord))
					return selectCommand(x);
			}
		}
		// throw new Error("Command not found");
		throw new UnrecognizedCommandException(firstWord);
	}
	
	private CommandInterface selectCommand(String x) throws LibraryLookUpException {
		for (String z : resource.keySet()) {
			if (x.equals(resource.getString(z))) {
				return createCommand(z);
			}
		}
		throw new Error("back end error");
	}

	private CommandInterface createCommand(String commandName) throws LibraryLookUpException {
		if ("Forward Backward".contains(commandName))
			return new ForwardCommand("Forward".contains(commandName));

		if ("Left Right".contains(commandName))
			return new RotationCommand("Right".contains(commandName));

		throw new LibraryLookUpException(commandName);
	}
}
