package back_end.commands.commandLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.CommandFactory;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

/**
 * 
 * 
 *
 */
public abstract class SimpleParameterCommand implements CommandInterface {
	private List<Double> myParams;

	private int getParamNum(ExpressionTree myTree, String currentLanguage) throws UnrecognizedCommandException {
		CommandFactory commandLib = new CommandFactory(currentLanguage);
		return commandLib.getNumParam(myTree.getRootNode().getInput().getParameter());
	}

	private void prepareChildren(ExpressionTree myTree, Model model) throws VariableNotFoundException, CommandException {
		for (ExpressionTreeNode kid : myTree.getRootNode().getChildren()) {
			myTree.traverseKid(kid, model);
		}
		int paramNum = getParamNum(myTree, myTree.getLanguage());
		if (paramNum != myTree.getRootNode().getChildren().size())
			throw new NotEnoughParameterException(myTree.getRootNode().getInput().getParameter(), paramNum,
					myTree.getRootNode().getChildren().size());
	}

	/**
	 * The method takes in a subtree of a command node and sets its parameters
	 * by traversing each child. Only once every child has returned a double
	 * value will the parameters be set.
	 * 
	 * @params model changes the current Model every time a child is traversed
	 * @params ExpressionTree... ds takes in an array of trees, extracts the
	 *         first one, and traverses it to obtain the necessary parameters
	 */
	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		ExpressionTree myTree = tree;
		prepareChildren(myTree, model);
		myParams = new ArrayList<Double>();
		for (ExpressionTreeNode child : myTree.getRootNode().getChildren()) {
			myParams.add(child.getOxygen().getReturnValue());
		}

		myParams.toArray(new Double[myParams.size()]);
	}

	protected List<Double> getParameterValue() {
		return Collections.unmodifiableList(myParams);
	}

	@Override
	public abstract double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException;
}
