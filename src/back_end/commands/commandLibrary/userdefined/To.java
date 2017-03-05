package back_end.commands.commandLibrary.userdefined;

import java.util.ArrayList;
import java.util.Iterator;

import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;
import back_end.commands.CustomCommand;
import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;

public class To implements CommandInterface {
	private ExpressionTree tree;

	public void setParameters(Model model, ExpressionTree tree) throws NotEnoughParameterException {
		Iterator<ExpressionTreeNode> iter = tree.getRootNode().getChildren().iterator();
		ExpressionTree mySubtree = new ExpressionTree(iter.next(), tree.getLanguage());
		this.tree = mySubtree;
	}

	public double Execute(Model model) {
		Iterator<ExpressionTreeNode> iterator = mainTree.getRootNode().getChildren().iterator();
		ArrayList<String> variableNames = new ArrayList<String>();

		for (ExpressionTreeNode x : iterator.next().getChildren()) {
			variableNames.add(x.getInput().getParameter());
		}
		try {
			model.addCustomCommand(mainTree.getRootNode().getInput().getParameter(),
					new CustomCommand(variableNames, new ExpressionTree(iterator.next(), model.getCurrentLanguage())));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}