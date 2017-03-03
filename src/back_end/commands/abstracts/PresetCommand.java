package back_end.commands.abstracts;

import java.util.ArrayList;
import java.util.List;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.CommandLibrary;
import back_end.model.ExpressionTree;
import back_end.model.ExpressionTreeNode;
import back_end.model.Model;
import back_end.model.Oxygen;

public abstract class PresetCommand implements CommandInterface<ExpressionTree>{
	private Double[] parameters;

	
	private int getParamNum(ExpressionTree myTree, String currentLanguage) throws UnrecognizedCommandException{
		CommandLibrary commandLib = new CommandLibrary(currentLanguage);
		  return commandLib.getNumParam(myTree.getRootNode().getInput().getParameter());
	}
	
	private void extractParams(ExpressionTree myTree, Model model) throws VariableNotFoundException, CommandException {
		for (ExpressionTreeNode kid : myTree.getRootNode().getChildren()) {
			myTree.traverseKid(kid, model);
		}
		int paramNum = getParamNum(myTree,myTree.getLanguage());
		if (paramNum != myTree.getRootNode().getChildren().size())
			throw new NotEnoughParameterException(myTree.getRootNode().getInput().getParameter(), 
													paramNum, myTree.getRootNode().getChildren().size());
	}
	/**
	 * The method takes in a subtree of a command node and sets its parameters by traversing each child. Only
	 * once every child has returned a double value will the parameters be set.
	 * @params model changes the current Model every time a child is traversed
	 * @params ExpressionTree... ds takes in an array of trees, extracts the first one, and traverses it to obtain
	 * the necessary parameters
	 */
	public void setParameters(Model model, ExpressionTree...ds) throws VariableNotFoundException, CommandException{
		ExpressionTree myTree = ds[0];
		extractParams(myTree,model);
		List<Double> myParams = new ArrayList<Double>();
		for(ExpressionTreeNode child: myTree.getRootNode().getChildren()){
			myParams.add(child.getOxygen().getReturnValue());
		}

		parameters = myParams.toArray(new Double[myParams.size()]);
	}
		
	protected Double[] getParameterValue() {
		return parameters;
	}
}
