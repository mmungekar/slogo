package back_end.model.expressiontree.node;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.libraries.CustomCommandLibrary;
import back_end.model.container.Input;
import back_end.model.scene.Model;

public class RootNode extends TreeNode {
	public RootNode(Input input) throws CommandException {
		super(input);
	}

	@Override
	public boolean isChildrenFull(CustomCommandLibrary ccl) {
		return false;
	}

	@Override
	public double traverse(Model state) throws CommandException, VariableNotFoundException {
		// TODO Auto-generated method stub
		double ret = -1d;
		for (TreeNode node : this.getChildren()) {
			ret = node.getValue();
		}
		this.setValue(ret);
		return ret;
	}
}
