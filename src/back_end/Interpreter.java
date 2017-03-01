package back_end;

public class Interpreter {
	private ExpressionTree mTree;	

	public String execute(Model model, String command) throws UnrecognizedCommandException, NotEnoughParameterException {
		mTree = new ExpressionTree(model.getCurrentLanguage());
		mTree.constructTree(command);
		return mTree.traverse(model);
	}

}
