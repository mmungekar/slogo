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
	private ExpressionTreeNode mCommandNode;
	private double limit;
	private String variable;
	public static final Double start = 1d;
	public static final Double increment = 1d;
	
	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		extractIterators(tree);
		Iterator<ExpressionTreeNode> iter1 = getIter1();
		Iterator<ExpressionTreeNode> iterVar = getIterVar();
		variable = (String)iterVar.next().getOxygen().getContent();
		limit = (Double)iterVar.next().getOxygen().getContent();
		mCommandNode = iter1.next();
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		return this.sendToForLoop(model, start, limit + 1, increment, variable, getTree(), mCommandNode);
	}
	
	

}
