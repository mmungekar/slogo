package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;


public class IfElse implements CommandInterface {
	private ExpressionTree myTree;
	
	@Override
	public void setParameters(Model model, ExpressionTree tree) throws NotEnoughParameterException {
			myTree = tree;
		
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Iterator<TreeNode> iter = myTree.getRootNode().getChildren().iterator();
		TreeNode firstChild = iter.next();
		myTree.traverseKid(firstChild,model);
		if(firstChild.getValue()==0){
			iter.next();
		}
		TreeNode toBeExecuted = iter.next();
		myTree.traverseKid(toBeExecuted,model);
		return toBeExecuted.getValue();	
	}

	
}
