package back_end.commands.commandLibrary.logic;


// This entire file is part of my masterpiece.
// Yuansong Feng
/**
 * I think this part of the code is good because it is really easy to extend and make
 * other useful functions including For loop, While loop and ForEach loop upon it. It extracts
 * out all the useful functions regarding iteration and demonstrates the open-close principle
 * of our program. 
 **/
import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.libraries.VariableLibrary;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public abstract class Iteration {
	private ExpressionTree mTree;
	private Iterator<ExpressionTreeNode> iter1;
	private Iterator<ExpressionTreeNode> iterVar;
	

	protected void repeat(Model state, ExpressionTree tree, ExpressionTreeNode node) throws VariableNotFoundException, CommandException{
		tree.traverseKid(node, state);
	}

	protected void assignLocalVariable(Model state, String name, Double value){
		VariableLibrary mLocalVarLib = state.getCustomMaster().getLocalVariableLibrary();
		if(mLocalVarLib.containsKey(name)){
			mLocalVarLib.remove(name);
		}
		mLocalVarLib.insertVariable(name, value);
	}
	
	protected Double sendToForLoop(Model model, Double start, Double end, Double increment, String variable, ExpressionTree mTree, ExpressionTreeNode mExprNode) throws VariableNotFoundException, CommandException{
		for(Double count = start; count < end; count += increment){
			assignLocalVariable(model, variable, count);
			repeat(model, mTree, mExprNode);
		}
		return mExprNode.getOxygen().getReturnValue();
	}
	
	protected void extractIterators(ExpressionTree tree){
		this.mTree = tree;
		ExpressionTreeNode root = tree.getRootNode();
		iter1 = root.getChildren().iterator();
		ExpressionTreeNode listStart = iter1.next();
		iterVar = listStart.getChildren().iterator();
	}
	
	protected ExpressionTree getTree() {
		return mTree;
	}

	protected Iterator<ExpressionTreeNode> getIter1() {
		return iter1;
	}

	protected Iterator<ExpressionTreeNode> getIterVar() {
		return iterVar;
	}
}
