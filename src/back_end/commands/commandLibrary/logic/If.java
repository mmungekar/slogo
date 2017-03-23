package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class If extends Conditional implements CommandInterface {
	
	@Override
	protected Double processConditional(Model model, ExpressionTree myTree2, ExpressionTreeNode firstChild2,
			Iterator<ExpressionTreeNode> iter2) throws VariableNotFoundException, CommandException {
		ExpressionTreeNode commandNode = iter2.next();
		if(firstChild2.getOxygen().getReturnValue() != 0){
			myTree2.traverseKid(commandNode, model);
		}
		return commandNode.getOxygen().getReturnValue();	
	}

	
}
