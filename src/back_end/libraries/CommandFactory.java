/**
 * This entire file is part of my code masterpiece
 * 
 * This file was selected for the masterpiece because it demonstrates the use of reflection while instantiating commands. Methods
 * are concise and short; the code demonstrates how commands are checked for validity. This, in conjunction with the other command files
 * in the masterpiece, demonstrate how commands are created and structured.
 */
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
	public static final String PARAMETER_DIRECTORY = "resources/parameters/Parameter";
	public static final String COMMAND_PREFIX = "resources.commands.commandpath";
	private ResourceBundle COMMAND_PATHS = ResourceBundle.getBundle(COMMAND_PREFIX);
	private ProgramParser mParser;
	private String currentLanguage;	
	
/**
 * A CommandFactory must be given a language in order to generate a command
 * @param language
 */
	public CommandFactory(String language) {
		this.currentLanguage = language;
		this.buildLib();
	}
/**
 * The resource bundles are initialized, and valid command names are extracted from the program parser
 */
	public void buildLib() {
		commandNames = new ArrayList<String>();

		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + this.currentLanguage);
		presource = ResourceBundle.getBundle(PARAMETER_DIRECTORY);
		mParser = new ProgramParser();
		mParser.addPatterns(LANGUAGE_DIRECTORY + this.currentLanguage);
		for (String x : resource.keySet()) {
			commandNames.add(x);
		}
	}
/**
 * Reflection is used to instantiate a command. If the command is not found, an UnrecognizedCommandException is thrown;
 * if the command could not be initialized, a custom InitializationException is thrown
 * @param command is the string that is the class name of the command
 * @return
 * @throws CommandException
 */
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
/**
 * The method returns the number of parameters the command ought to have, in order to check that the command
 * has the right amount of parameters
 * @param command is the string that is the class name of the command
 * @return
 * @throws UnrecognizedCommandException
 */
	public int getNumParam(String command) throws UnrecognizedCommandException {
		String official = mParser.getSymbol(command);
		return Integer.parseInt(presource.getString(official));
	}
}