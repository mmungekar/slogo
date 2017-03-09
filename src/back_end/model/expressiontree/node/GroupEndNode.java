package back_end.model.expressiontree.node;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.libraries.CustomCommandLibrary;
import back_end.model.container.Input;
import back_end.model.scene.Model;

public class GroupEndNode extends TreeNode{

	public GroupEndNode(Input x) throws CommandException {
		super(x);
	}

	@Override
	public boolean isChildrenFull(CustomCommandLibrary ccl) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * For group end node, we append it as the last child to
	 * the group start node and move the cursor up to the group start
	 */
	@Override 
	public TreeNode appendTo(TreeNode parent){
		parent.addChild(this);
		this.setParent(parent);
		return parent.getParent();
	}

	@Override
	public double traverse(Model state) throws CommandException, VariableNotFoundException {
		return 0;
	}
}
