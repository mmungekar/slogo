package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;


public class IfElse extends Conditional implements CommandInterface {

	@Override
	protected Double processConditional(Model model, ExpressionTree myTree2, ExpressionTreeNode firstChild2,
			Iterator<ExpressionTreeNode> iter2) throws VariableNotFoundException, CommandException {
		myTree2.traverseKid(firstChild2,model);
		if(firstChild2.getOxygen().getReturnValue()==0){
			iter2.next();
		}
		ExpressionTreeNode toBeExecuted = iter2.next();
		myTree2.traverseKid(toBeExecuted,model);
		return toBeExecuted.getOxygen().getReturnValue();	
	}

	
}
