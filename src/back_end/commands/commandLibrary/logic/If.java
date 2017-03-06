package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class If implements CommandInterface {
	private ExpressionTree myTree;
	
	@Override
	public void setParameters(Model model, ExpressionTree tree) throws NotEnoughParameterException {
			myTree = tree;
		
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Iterator<ExpressionTreeNode> iter = myTree.getRootNode().getChildren().iterator();
		ExpressionTreeNode firstChild = iter.next();
		myTree.traverseKid(firstChild,model);
		ExpressionTreeNode commandNode = iter.next();
		if(firstChild.getOxygen().getReturnValue() != 0){
			myTree.traverseKid(commandNode, model);
		}
		return commandNode.getOxygen().getReturnValue();	
	}

	
}
