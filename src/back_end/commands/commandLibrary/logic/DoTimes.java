package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import com.sun.org.apache.xpath.internal.ExpressionNode;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.VariableLibrary;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;

public class DoTimes extends Iteration implements CommandInterface{
	private ExpressionTree mTree;
	private TreeNode mCommandNode;
	private double limit;
	private String variable;
	
	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		// TODO Auto-generated method stub
		mTree = tree;
		TreeNode root = mTree.getRootNode();
		Iterator<TreeNode> iter1 = root.getChildren().iterator();
		TreeNode listStart = iter1.next();
		Iterator<TreeNode> iterVar = listStart.getChildren().iterator();
		variable = (String)iterVar.next().getName();
		limit = (Double)iterVar.next().getValue();
		mCommandNode = iter1.next();
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		for(Double count = 1d; count < limit + 1; count++){
			assignLocalVariable(model, variable, count);
			repeat(model, mTree, mCommandNode);
		}
		return mCommandNode.getValue();
	}
	
	

}
