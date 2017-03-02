package back_end.overhead;


import back_end.model.ExpressionTree;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.model.Model;

public class Interpreter {
	private ExpressionTree mTree;	


	public String execute(Model model, String command) throws CommandException, VariableNotFoundException {
		mTree = new ExpressionTree(model.getCurrentLanguage());
		mTree.constructTree(command);
		return mTree.traverse(model);
	}

}
