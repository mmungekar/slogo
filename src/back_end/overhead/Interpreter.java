package back_end.overhead;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import back_end.commands.constant.Constant;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.model.container.Input;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class Interpreter {
	private static final String SYNTAX = "resources/languages/Syntax";
	private ExpressionTree mTree;	
	private ProgramParser mParser;
	
	public Interpreter(){
		mParser = new ProgramParser();
		mParser.addPatterns(SYNTAX);
	}

	public double execute(Model model, String command) throws CommandException, VariableNotFoundException {
        List<Input> inputs = translate(command);
		mTree = new ExpressionTree(null, model.getCurrentLanguage(), model.getCustomMaster().getCustomCommandLibrary());
		mTree.constructTree(inputs);
		return mTree.traverse(model);
	}

	public List<Input> translate(String commandString) throws UnrecognizedCommandException {
		List<Input> inputs = new ArrayList<>();
		Scanner cScanner = new Scanner(commandString);
		// Parse string into inputs, getting rid of Comment type
		while (cScanner.hasNext()) {
			String in = cScanner.next().trim().toLowerCase();
			String type = mParser.getSymbol(in);
			//System.out.println("" + in + " -> Type: " + type);
			if (type.equals(Constant.COMMENT_TYPE)) {
				cScanner.nextLine();
				continue;
			}
			Input input = new Input(in, type);
			inputs.add(input);
		}
		cScanner.close();
		return inputs;
	}
}
