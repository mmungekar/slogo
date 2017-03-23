/**
 * This entire file is part of my code masterpiece
 * 
 * This abstract class encapsulates any command which specifies a list of turtle ID's. It demonstrates the role of the CommandInterface
 * class within the command hierarchy and reduces repeated code in the actual command classes. This file was selected for the masterpiece 
 * because it contains concise syntax and readable methods. The actual implementation of this interface can be found in Ask and Tell.
 */
package back_end.commands.commandLibrary;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;
/**
 * By Mina Mungekar
 * Traverses the first (bracketed) child of the expression tree to obtain its parameters
 */
public abstract class MultipleTurtleCommand implements CommandInterface{
	private ExpressionTree myTree;
	private Iterator<ExpressionTreeNode> iter;
	private List<Integer> myParamValues;
/**
 * This method accepts the current program Model and an ExpressionTree. The children of the first child of this tree are all
 *  turtle ID numbers indicating which turtles are to be activated. These ID numbers are extracted and made into a list.
 */
	@Override
	public void setParameters(Model model, ExpressionTree tree) throws VariableNotFoundException, CommandException {
			myTree = tree;
			iter = myTree.getRootNode().getChildren().iterator();
			ExpressionTreeNode firstChild = iter.next();
			myTree.traverseKid(firstChild,model);
			myParamValues = firstChild.getChildren().stream() .filter(elt -> elt != null)
					.map(elt -> elt.getOxygen().getReturnValue().intValue()).collect(Collectors.toList());

	}
	/**
	 * This method returns the list of turtle IDs obtained from the command
	 */
	public List<Integer> getMyParamValues(){
		return myParamValues;
	}
	/**
	 * This method returns the ExpressionTree holding the command
	 */
	public ExpressionTree getTree(){
		return myTree;
	}
	/**
	 * This method returns the iterator itself, so that the remaining branches of the tree can be traversed
	 */
	public Iterator<ExpressionTreeNode> getIterator(){
		return iter;
	}
}
