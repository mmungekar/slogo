package back_end.commands.commandLibrary.userdefined;

import java.util.Iterator;

import back_end.commands.constant.Constant;
import back_end.commands.custom.CustomCommand;
import back_end.commands.custom.CustomVariable;
import back_end.exceptions.CommandException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.VariableLibrary;
import back_end.model.container.Pair;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;

public class MakeUserInstruction implements CommandInterface {
	private ExpressionTree mTree;
	private String commandName;
	private Pair<VariableLibrary, ExpressionTree> mVarTreePair;

	public void setParameters(Model model, ExpressionTree tree) throws CommandException {
		this.mTree = tree;
		VariableLibrary customVarLib = new VariableLibrary();
		TreeNode mRootNode = tree.getRootNode();
		Iterator<TreeNode> iter = mRootNode.getChildren().iterator();
		commandName = (String) iter.next().getName();
		customVarLib = getCustomVarLib(iter.next());
		ExpressionTree commandTree = new ExpressionTree(iter.next(), mTree.getLanguage(),
				model.getCustomCommandLibrary());
		mVarTreePair = new Pair<>(customVarLib, commandTree);
		// Move from execute to setParameters
		CustomCommand command = new CustomCommand(commandName, mVarTreePair.getA(), mVarTreePair.getB());
		if (model.getCustomCommandLibrary().contains(commandName))
			model.getCustomCommandLibrary().remove(commandName);
		model.getCustomCommandLibrary().put(commandName, command);
	}

	private VariableLibrary getCustomVarLib(TreeNode node) {
		VariableLibrary mVarLib = new VariableLibrary();
		for (TreeNode varNode : node.getChildren()) {
			if (!varNode.getType().equals(Constant.LISTEND_TYPE))
				mVarLib.put((String) varNode.getName(),0d);
		}
		return mVarLib;

	}

	/**
	 * Add the new Custom Command to the CustomCommandLibrary in the model
	 */
	public double Execute(Model model) {
		if (model.getCustomCommandLibrary().contains(commandName)) {
			System.out.println("Put new command: " + commandName + " into the CustomCommandLib.");
			return 1;
		}
		return 0;
	}
}