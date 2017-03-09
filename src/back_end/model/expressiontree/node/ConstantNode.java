package back_end.model.expressiontree.node;

import back_end.exceptions.CommandException;
import back_end.libraries.CustomCommandLibrary;
import back_end.model.container.Input;
import back_end.model.scene.Model;

public class ConstantNode extends TreeNode{
	public ConstantNode(Input x) throws CommandException {
		super(x);
		this.setValue(Double.parseDouble(x.getParameter()));
	}

	@Override
	public boolean isChildrenFull(CustomCommandLibrary ccl) {
		return true;
	}

	@Override
	public double traverse(Model state) {
		return this.getValue();
	}
	

}
