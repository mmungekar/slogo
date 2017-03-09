package back_end.model.expressiontree.node;

import back_end.exceptions.CommandException;
import back_end.libraries.CustomCommandLibrary;
import back_end.model.container.Input;
import back_end.model.scene.Model;

public class VariableNode extends TreeNode{

	public VariableNode(Input x) throws CommandException {
		super(x);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isChildrenFull(CustomCommandLibrary ccl) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double traverse(Model state) {
		if(state.isVariableStored(getName()))
			this.setValue(state.retrieveVariable(getName()));
		return state.retrieveVariable(getName());
	}

}
