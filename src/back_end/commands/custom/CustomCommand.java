package back_end.commands.custom;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.VariableLibrary;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

/**
 * 
 * @author Juan Philippe
 *
 */
public class CustomCommand implements CommandInterface {
	private String mName;
	private VariableLibrary mCustomVarLib;
	private ExpressionTree mCommandTree;
	public CustomCommand(String name, VariableLibrary varLib, ExpressionTree tree) {
		mName = name;
		mCustomVarLib = varLib;
		mCommandTree = tree;
	}

	@Override
	public void setParameters(Model model, ExpressionTree tree) throws NotEnoughParameterException {
		ExpressionTreeNode root = tree.getRootNode();
		Iterator<ExpressionTreeNode> valIter = root.getChildren().iterator();
		Iterator<String> nameIter = mCustomVarLib.keySet().iterator();
		while (valIter.hasNext()) {
			mCustomVarLib.insertVariable(nameIter.next(), (Double) valIter.next().getOxygen().getContent());
		}
		if(nameIter.hasNext())
			throw new NotEnoughParameterException("Missing parameters for the custom command: " + mName, 
					mCustomVarLib.keySet().size(), root.getChildren().size());

	}
	
	public String getName(){
		return mName;
	}
	
	public int getNumParams(){
		return mCustomVarLib.keySet().size();
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException {
		model.getCustomMaster().setLocalVariableLibrary(mCustomVarLib);
		double retVal = mCommandTree.traverse(model);
		model.getCustomMaster().setLocalVariableLibrary(null);
		return retVal;

	}

}
