package back_end.commands.commandLibrary.logic;

// This entire file is part of my masterpiece.
// Yuansong Feng

/**
 * This is just one of many examples of possible uses of the Iteration super class. 
 * Since most of the related functions are implemented in the Iteration class already,
 * For class is surprisingly small and succinct. 
 */

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class For extends Iteration implements CommandInterface{
	private ExpressionTreeNode mExprNode;
	private double start;
	private double end;
	private double increment;
	private String variable;
	
	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		extractIterators(tree);
		Iterator<ExpressionTreeNode> iter1 = getIter1();
		Iterator<ExpressionTreeNode> iterVar = getIterVar();
		variable = (String)iterVar.next().getOxygen().getContent();
		start = (Double)iterVar.next().getOxygen().getContent();
		end = (Double)iterVar.next().getOxygen().getContent();
		increment = (Double)iterVar.next().getOxygen().getContent();
		mExprNode = iter1.next();
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {		
		return this.sendToForLoop(model, start, end, increment, variable, getTree(), mExprNode);
	}
	
	

}
