package back_end;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import commands.Command;
/**
 * The StringInterpreter class receives user-input commands, 
 * and parses through them to determine command type and parameter 
 *
 */
public class StringInterpreter
{
	private final String FILEPATH = "resources/languages/Syntax";
	private ProgramParser myParser;
	private CommandLibrary commandLib;
	private List<Input> myInputs;
	Scanner scanner;

	/**
	 * The StringInterpreter constructor initiates a command library, a parser to 
	 * go through the user's input, and a map to store the index number of the user-entered input 
	 * along with its type (i.e., variable, integer, command, etc...)
	 */
	public StringInterpreter()
	{
		commandLib = new CommandLibrary();
		myParser = new ProgramParser();
		myParser.addPatterns(FILEPATH);
	    myInputs = new ArrayList<Input>();
	}

	/**
	 * Added the following two methods to store every input parameter as well as its type
	 *@param String commandLine and commandString refer to the user-input commands. Everything bounded
	 *by whitespace characters is parsed, stored as an input object, and put into a list.
	 */
	private void parseLine(String commandLine){
		Scanner lineScanner = new Scanner(commandLine);
		Input newInput = new Input(lineScanner.next(), myParser.getSymbol(lineScanner.next()));
		myInputs.add(newInput);
	}
	
	private void storeInput(String commandString){
		Scanner inputScanner = new Scanner(commandString);
		while (inputScanner.hasNextLine()) {
            parseLine(inputScanner.nextLine());
		}
	}
	
	public Command interpret(String commandString)
	{
		System.out.println("Text submitted: " + commandString);

		scanner = new Scanner(commandString);
		//NEEDS TO BE ALTERED IF REPETITION
		Command command = commandLib.getCommand(scanner.next());
		command.setParameters(scanner.nextLine());
		return command;
	}
	
}
