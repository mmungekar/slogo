package back_end.commands.commandLibrary.userdefined;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;

public class MakeVariable implements CommandInterface {
	private String name;
	private double value;

	@Override
	public void setParameters(Model model, ExpressionTree tree) throws CommandException, VariableNotFoundException {
		TreeNode mRoot = tree.getRootNode();
		Iterator<TreeNode> iter = mRoot.getChildren().iterator();
		TreeNode node = iter.next();
		name = node.getName();
		TreeNode second = iter.next();
		second.traverse(model);
		value = second.getValue();
	}

	@Override
	public double Execute(Model model) {
		model.updateVariable(name, value);
		System.out.println("New variable created: " + name + " : " + value);
		return value;
	}

}
