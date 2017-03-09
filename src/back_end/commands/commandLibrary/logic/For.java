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

public class For extends Iteration implements CommandInterface{
	private ExpressionTree mTree;
	private TreeNode mExprNode;
	private double start;
	private double end;
	private double increment;
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
		start = (Double)iterVar.next().getValue();
		end = (Double)iterVar.next().getValue();
		increment = (Double)iterVar.next().getValue();
		mExprNode = iter1.next();
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		for(Double count = start; count < end; count += increment){
			assignLocalVariable(model, variable, count);
			repeat(model, mTree, mExprNode);
		}
		return mExprNode.getValue();
	}
	
	

}
