package back_end.commands.commandLibrary.logic;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;

public class Repeat extends Iteration implements CommandInterface{
    private static final String varName = ":repcount";
    private int mRepeat;
    private ExpressionTree mTree;
    private TreeNode mExpr;
    private TreeNode mCommand;
	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		mTree = tree;
		TreeNode root = mTree.getRootNode();
		Iterator<TreeNode> iter1 = root.getChildren().iterator();
		mExpr = iter1.next();
		mCommand = iter1.next();
		mTree.traverseKid(mExpr, model);
		mRepeat = mExpr.getValue().intValue();
		
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		for(Integer count = 0; count < mRepeat; count++){
			assignLocalVariable(model, varName, count.doubleValue());
			repeat(model, mTree,mCommand);
		}
		return mCommand.getValue();
	}

}
