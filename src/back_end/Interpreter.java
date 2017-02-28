package back_end;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import back_end.commands.CommandInterface;

/**
 * The StringInterpreter class receives Inputs, and parses through them to
 * determine command type and parameter
 *
 */
public class Interpreter {
	private ExpressionTree mTree;
	private String language;

	/**
	 * The StringInterpreter constructor initiates a command library, a parser
	 * to go through the user's input, and a map to store the index number of
	 * the user-entered input along with its type (i.e., variable, integer,
	 * command, etc...)
	 */
	public Interpreter(String lang) {
		language = lang;
		
	}

	public void execute(Model model, String command) throws UnrecognizedCommandException, NotEnoughParameterException {
		mTree = new ExpressionTree(language);
		mTree.constructTree(command);
		mTree.traverse(model);
	}

}
