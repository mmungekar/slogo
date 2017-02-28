package back_end;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import commands.CommandInterface;
import commands.ParameterException;

/**
 * The StringInterpreter class receives Inputs, and parses through them to
 * determine command type and parameter
 *
 */
public class Interpreter {
	private final String FILEPATH = "resources/languages/Syntax";
	private ProgramParser myParser;
	private CommandLibrary commandLib;
	private List<Input> myInputs;
	Scanner scanner;

	/**
	 * The StringInterpreter constructor initiates a command library, a parser
	 * to go through the user's input, and a map to store the index number of
	 * the user-entered input along with its type (i.e., variable, integer,
	 * command, etc...)
	 */
	public Interpreter() {
		commandLib = new CommandLibrary();
		myParser = new ProgramParser();
		myParser.addPatterns(FILEPATH);
		myInputs = new ArrayList<Input>();
	}

	/**
	 * Added the following two methods to store every input parameter as well as
	 * its type
	 * 
	 * @param String
	 *            commandLine and commandString refer to the user-input
	 *            commands. Everything bounded by whitespace characters is
	 *            parsed, stored as an input object, and put into a list.
	 * @throws UnrecognizedCommandException
	 */
	private void storeInput(String commandString) throws UnrecognizedCommandException {
		Scanner inputScanner = new Scanner(commandString);
		while (inputScanner.hasNextLine()) {
			parseLine(inputScanner.nextLine());
		}
		inputScanner.close();
	}

	private void parseLine(String commandLine) throws UnrecognizedCommandException {
		Scanner lineScanner = new Scanner(commandLine);
		Input newInput = new Input(lineScanner.next().trim().toLowerCase(), myParser.getSymbol(lineScanner.next()));
		myInputs.add(newInput);
		lineScanner.close();
	}

	/**
	 * TODO: Currently not able to match comment type
	 * 
	 * @param s
	 * @return
	 * @throws UnrecognizedCommandException
	 */
	public String getType(String s) throws UnrecognizedCommandException {
		return myParser.getSymbol(s);
	}

	public CommandInterface translateInput(Input commandInput) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnrecognizedCommandException {
		CommandInterface command = commandLib.getCommand(commandInput.getParameter());
		return command;
	}

}
