package back_end.commands.commandLibrary.userdefined;

import java.util.Iterator;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class MakeVariable implements CommandInterface {
	private String name;
	private double value;

	@Override
	public void setParameters(Model model, ExpressionTree tree) throws VariableNotFoundException, CommandException {
		ExpressionTreeNode mRoot = tree.getRootNode();
		Iterator<ExpressionTreeNode> iter = mRoot.getChildren().iterator();
		Oxygen oxygen = iter.next().getOxygen();
		try {
			name = (String) oxygen.getContent();
		} catch (ClassCastException ex) {
			name = oxygen.getSubContent();
		}
		ExpressionTreeNode eval = iter.next();
		tree.traverseKid(eval,model);
		value = (Double)eval.getOxygen().getReturnValue();
		//value = (Double) iter.next().getOxygen().getContent();
	}

	@Override
	public double Execute(Model model) {
		model.getCustomMaster().updateVariable(name, value);
		System.out.println("New variable created: " + name + " : " + value);
		return value;
	}

}
