package back_end.commands.commandLibrary.logic;


import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.VariableLibrary;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public abstract class Iteration {

	protected void repeat(Model state, ExpressionTree tree, ExpressionTreeNode node) throws VariableNotFoundException, CommandException{
		tree.traverseKid(node, state);
	}

	protected void assignLocalVariable(Model state, String name, Double value){
		VariableLibrary mLocalVarLib = state.getLocalVariableLibrary();
		if(mLocalVarLib.containsKey(name)){
			mLocalVarLib.remove(name);
		}
		mLocalVarLib.insertVariable(name, value);
	}
}
