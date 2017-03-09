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

public class AskWith implements CommandInterface{
	List<Integer> validTurtles = new ArrayList<Integer>();
	Iterator<ExpressionTreeNode> iter;
	ExpressionTree myTree;
	
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
	
	@Override
	public double Execute(Model model) throws VariableNotFoundException, CommandException {
		model.tellTemps(validTurtles);
		ExpressionTreeNode secondChild = iter.next();
		myTree.traverseKid(secondChild,model);
		model.revertActiveTurtles();
		return secondChild.getOxygen().getReturnValue();
		//return model.getSingleActiveTurtle();
	}

}
