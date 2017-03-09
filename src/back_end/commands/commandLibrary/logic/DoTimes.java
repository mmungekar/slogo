package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class DoTimes extends Iteration implements CommandInterface{
	private ExpressionTree mTree;
	private ExpressionTreeNode mCommandNode;
	private double limit;
	private String variable;
	
	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		// TODO Auto-generated method stub
		mTree = tree;
		ExpressionTreeNode root = mTree.getRootNode();
		Iterator<ExpressionTreeNode> iter1 = root.getChildren().iterator();
		ExpressionTreeNode listStart = iter1.next();
		Iterator<ExpressionTreeNode> iterVar = listStart.getChildren().iterator();
		variable = (String)iterVar.next().getOxygen().getContent();
		limit = (Double)iterVar.next().getOxygen().getContent();
		mCommandNode = iter1.next();
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		for(Double count = 1d; count < limit + 1; count++){
			assignLocalVariable(model, variable, count);
			repeat(model, mTree, mCommandNode);
		}
		return mCommandNode.getOxygen().getReturnValue();
	}
	
	

}
