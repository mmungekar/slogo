package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public abstract class Conditional implements CommandInterface{
	private ExpressionTree myTree;
	private ExpressionTreeNode firstChild;
	private Iterator<ExpressionTreeNode> iter;

	@Override
	public void setParameters(Model model, ExpressionTree tree) throws NotEnoughParameterException {
			myTree = tree;
			iter = myTree.getRootNode().getChildren().iterator();
			firstChild = iter.next();
	}
		
	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		myTree.traverseKid(firstChild,model);
		return processConditional(model, myTree, firstChild, iter);	
	}

	protected abstract Double processConditional(Model model, ExpressionTree myTree2, ExpressionTreeNode firstChild2,
			Iterator<ExpressionTreeNode> iter2) throws VariableNotFoundException, CommandException;
}
