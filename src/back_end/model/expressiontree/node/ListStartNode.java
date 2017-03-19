package back_end.model.expressiontree.node;

import java.util.Iterator;

import back_end.commands.constant.Constant;
import back_end.commands.custom.CustomCommand;
import back_end.commands.custom.CustomVariable;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.VariableLibrary;
import back_end.model.container.Input;
import back_end.model.scene.Model;

public class ListStartNode extends TreeNode{

	public ListStartNode(Input x) throws CommandException {
		super(x);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public TreeNode appendTo(TreeNode parent, Model state){
		if(parent.getName().equals("MakeUserInstruction") && parent.getChildren().size() == 2){
			Iterator<TreeNode> iter = parent.getChildren().iterator();
			String commandName = iter.next().getName();
			VariableLibrary varLib = getCustomVarLib(iter.next());
			CustomCommand command = new CustomCommand(commandName, varLib, null);
			state.getCustomCommandLibrary().put(commandName, command);
		}
		parent.addChild(this);
		this.setParent(parent);
		return this;
	}
	
	private VariableLibrary getCustomVarLib(TreeNode node) {
		VariableLibrary mVarLib = new VariableLibrary();
		for (TreeNode varNode : node.getChildren()) {
			if (!varNode.getType().equals(Constant.LISTEND_TYPE))
				mVarLib.put((String) varNode.getName(), 0d);
		}
		return mVarLib;

	}
	
	@Override
	public boolean isChildrenFull(CustomCommandLibrary ccl) {
//		if(getLastChild() == null)
//		    return false;
//		if(getLastChild().getType().equals(Constant.LISTEND_TYPE))
//			return true;
		return false;
	}
	
	/*private TreeNode getLastChild(){
		Iterator<TreeNode> childIter = getChildren().iterator();
		TreeNode lastChild = null;
		while(childIter.hasNext()){
			lastChild = childIter.next();
		}
		return lastChild;
	}*/

	@Override
	public double traverse(Model state) throws CommandException, VariableNotFoundException {
		double ret = -1d;
		for(TreeNode node : this.getChildren()){
			node.traverse(state);
			ret = node.getValue();
		}
		this.setValue(ret);
		return ret;
		
	}
	
	

}
