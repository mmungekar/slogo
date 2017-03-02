package back_end;

import back_end.commands.ExpressionTree;
import back_end.exceptions.CommandException;

public class Interpreter {
	private ExpressionTree mTree;	

	public double execute(Model model, String command) throws CommandException {
		mTree = new ExpressionTree(model.getCurrentLanguage());
		mTree.constructTree(command);
		return mTree.traverse(model);
	}

}
