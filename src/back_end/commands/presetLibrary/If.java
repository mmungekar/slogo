package back_end.commands.presetLibrary;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.ExpressionTreeNode;
import back_end.model.Model;

public class If implements CommandInterface<ExpressionTree> {
	private ExpressionTree myTree;
	private Model myModel;
	
	@Override
	public void setParameters(ExpressionTree... os) throws NotEnoughParameterException {
			myTree = os[0];
		
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Iterator<ExpressionTreeNode> iter = myTree.getRootNode().getChildren().iterator();
		ExpressionTreeNode firstChild = iter.next();
		myTree.traverseKid(firstChild,model);
		if((double)firstChild.getOxygen().getContent()==0){
			iter.next();	
		}
		ExpressionTreeNode toBeExecuted = iter.next();
		myTree.traverseKid(toBeExecuted,model);
		return (double) (toBeExecuted.getOxygen().getContent());
		
	}

	
}
