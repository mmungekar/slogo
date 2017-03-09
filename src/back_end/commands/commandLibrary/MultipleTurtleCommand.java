package back_end.commands.commandLibrary;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;
/**
 *Traverses the first (bracketed) child of the expression tree to obtain its parameters
 */
public abstract class MultipleTurtleCommand implements CommandInterface {
	private ExpressionTree myTree;
	private ExpressionTreeNode firstChild;
	private Iterator<ExpressionTreeNode> iter;
	
	@Override
	public void setParameters(Model model, ExpressionTree tree) throws VariableNotFoundException, CommandException {
			myTree = tree;
			iter = myTree.getRootNode().getChildren().iterator();
			firstChild = iter.next();
			myTree.traverseKid(firstChild,model);

	}
	
	public ExpressionTree getTree(){
		return myTree;
	}
	
	public ExpressionTreeNode getFirstChild(){
		return firstChild;
	}
	
	public Iterator<ExpressionTreeNode> getIterator(){
		return iter;
	}
}
