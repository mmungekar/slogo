package back_end.model.expressiontree.node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.NodeFactory;
import back_end.model.container.Input;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

/**
 * Constructs the nodes of the expression tree, which will contain either
 * commands, operators, or integer values (hence, type Object)
 */
public abstract class TreeNode{
	// Contents
	private TreeNode myParent;
	private List<TreeNode> myChildren;
	private String mType;
	private String mName;
	private Double mValue;
	private Input mInput;
	
	/**
	 * If the parent node is specified, it is included in the constructor
	 * @param string 
	 * 
	 * @param x
	 *            specifies the contents of the node; parent specifies the
	 *            parent node which operates upon the current node
	 * @throws CommandException 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public TreeNode(Input input) throws CommandException {
		myChildren = new ArrayList<TreeNode>();
		mInput = input;
		mName = input.getParameter();
		mType = input.getType();
		
	}

	public void addChild(TreeNode child) {
		if (this != null) {
			this.myChildren.add(child);
		}
	}

	/**
	 * Returns the parent of the current node
	 * 
	 * @return the ExpressionTreeNode which is parent to the current one
	 */
	public TreeNode getParent() {
		return this.myParent;
	}

	public void setParent(TreeNode parent) {
		myParent = parent;
	}

	public Collection<TreeNode> getChildren() {
		return myChildren;
	}
	
	/**
	 * Append the current node to a parent node and return the current node
	 * @param parent
	 * @return
	 */
	public TreeNode appendTo(TreeNode parent){
		parent.addChild(this);
		this.setParent(parent);
		return this;
	}

	public Double getValue(){
		return mValue;
	}
	
	public void setValue(double ret){
		mValue = ret;
	}
	
	public String getName(){
		return mName;
	}
	
	public void setName(String name){
		mName = name;
	}
	
	public Input getInput(){
		return mInput;
	}


	
	public String getType(){
		return mType;
	}
	
	public abstract boolean isChildrenFull(CustomCommandLibrary ccl);
	
	public abstract double traverse(Model state) throws CommandException, VariableNotFoundException;
	
}
