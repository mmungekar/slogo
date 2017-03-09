package back_end.commands.commandLibrary.multipleTurtles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;
/**
 * For all turtles, if the input expression is true, the given set of commands is executed
 */
public class AskWith implements CommandInterface{
	List<Integer> validTurtles = new ArrayList<Integer>();
	Iterator<ExpressionTreeNode> iter;
	ExpressionTree myTree;
/**
 * All turtles are set to being temporarily active, and the expression is evaluated for each one.
 * For any return value not equal to zero, the turtle is added to the List validTurtles
 */
	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		myTree = tree;
		 iter = myTree.getRootNode().getChildren().iterator();
		ExpressionTreeNode firstChild = iter.next();
		for(Integer id: model.getTurtleIDs()){
			model.tellTemps(Collections.singletonList(id));
			myTree.traverseKid(firstChild,model);
			if(firstChild.getOxygen().getReturnValue()!=0){
				validTurtles.add(id);
			}
		}
		
	}
/**
 * The validTurtle list is set to being temporarily active, and the user's commands are
 * executed for them
 */
	@Override
	public double Execute(Model model) throws VariableNotFoundException, CommandException {
		model.tellTemps(validTurtles);
		ExpressionTreeNode secondChild = iter.next();
		myTree.traverseKid(secondChild,model);
		model.revertActiveTurtles();
		return secondChild.getOxygen().getReturnValue();
	}

}
