package back_end.commands.commandLibrary.userdefined;

import java.util.ArrayList;
import java.util.Iterator;

import back_end.commands.CustomCommand;
import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.VariableLibrary;
import back_end.model.container.Pair;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class MakeUserInstruction implements CommandInterface {
	private ExpressionTree mTree;
	private String commandName;
	private Pair<VariableLibrary, ExpressionTree> mVarTreePair;

	public void setParameters(Model model, ExpressionTree tree) throws NotEnoughParameterException {
		this.mTree = tree;
		VariableLibrary customVarLib = new VariableLibrary();
		ExpressionTreeNode mRootNode = tree.getRootNode();
		Iterator<ExpressionTreeNode> iter = mRootNode.getChildren().iterator();
		commandName = (String) iter.next().getOxygen().getContent();
		customVarLib = getCustomVarLib(iter.next());
		iter.next();
		ExpressionTree commandTree = new ExpressionTree(iter.next(), mTree.getLanguage(), model.mCustomCommandLibrary);
		mVarTreePair = new Pair<>(customVarLib, commandTree);
	}
	
	private VariableLibrary getCustomVarLib(ExpressionTreeNode node){
		VariableLibrary mVarLib = new VariableLibrary();
	    for(ExpressionTreeNode varNode : node.getChildren()){
//	    	System.out.println("Build new custom var lib");
//	    	System.out.println("Var Name: " + varNode.getOxygen().getContent().toString());
	    	mVarLib.put((String)varNode.getOxygen().getContent(), 0d);
	    }
	    return mVarLib;
		
	}

	/**
	 * Add the new Custom Command to the CustomCommandLibrary in the model
	 */
	public double Execute(Model model) {
		CustomCommand command = new CustomCommand(commandName, mVarTreePair.getA(), mVarTreePair.getB());
		model.mCustomCommandLibrary.put(commandName, command);
//		System.out.println("Put new command: " + command + "into the CustomCommandLib.");
//		System.out.println("Needs " + command.getNumParams() + " parameters.");
		return 1d;
	}
}