package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;

public class If implements CommandInterface {
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
		TreeNode commandNode = iter.next();
		if(firstChild.getValue() != 0){
			commandNode.traverse(model);
		}
		System.out.println("Command is null: " + (commandNode == null) + " if value: " + commandNode.getValue());
		return commandNode.getValue();	
	}

	
}
