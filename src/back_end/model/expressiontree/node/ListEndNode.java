package back_end.model.expressiontree.node;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.libraries.CustomCommandLibrary;
import back_end.model.container.Input;
import back_end.model.scene.Model;

public class ListEndNode extends TreeNode{

	public ListEndNode(Input x) throws CommandException {
		super(x);
	}

	@Override
	public boolean isChildrenFull(CustomCommandLibrary ccl) {
		return true;
	}
	
	/**
	 * For list end node, we append it as the last child of the 
	 * list start and move the cursor to the list start node
	 */
	@Override 
	public TreeNode appendTo(TreeNode parent){
		return parent.getParent();
	}

	@Override
	public double traverse(Model state) throws CommandException, VariableNotFoundException {
		return 0;
	}

}
