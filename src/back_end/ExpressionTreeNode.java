package back_end;
	
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import back_end.commands.CommandInterface;
import back_end.constant.Constant;

/**
 * Constructs the nodes of the expression tree, which will contain either
 * commands, operators, or integer values (hence, type Object)
 */
public class ExpressionTreeNode {
	private CommandLibrary mCommandLib;
	// Contents
	private Input mInput;
	private CommandInterface mCommand;
	private double mValue;
	private ExpressionTreeNode myParent;
	private List<ExpressionTreeNode> myChildren;
	private boolean mExecuted;

	/**
	 * If the parent node is specified, it is included in the constructor
	 * 
	 * @param x
	 *            specifies the contents of the node; parent specifies the
	 *            parent node which operates upon the current node
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws UnrecognizedCommandException 
	 */
	public ExpressionTreeNode(Input x, ExpressionTreeNode parent)
			throws UnrecognizedCommandException {
		myChildren = new ArrayList<ExpressionTreeNode>();
		mCommandLib = new CommandLibrary();
		mCommand = null;
		mExecuted = false;
		myParent = parent;
		mInput = x;
		checkConstant(x);
		checkCommand(x);
	}

	private void checkConstant(Input x){
		if(x.getType() == Constant.CONSTANT_TYPE){
			mValue = Double.parseDouble(x.getParameter());
		}
	}
	
	private void checkCommand(Input x) throws UnrecognizedCommandException{
	if(x.getType() == Constant.COMMAND_TYPE){	
		mCommand = mCommandLib.getCommand(x.getParameter());
		}
	}
	
	/**
	 * Adds a child node to the current node
	 * @param child
	 */
		public void addChild(ExpressionTreeNode child) {
		if (this != null) {
			this.myChildren.add(child);
		}
	}

	/**
	 * Returns the parent of the current node
	 * @return the ExpressionTreeNode which is parent to the current one
	 */
	public ExpressionTreeNode getParent() {
		return this.myParent;
	}
	/**
	 * Sets a parent node for the current node
	 * @param parent
	 */
	public void setParent(ExpressionTreeNode parent){
		myParent = parent;
	}
	/**
	 * Returns the children of the current node
	 * @return myChildren
	 */
	public Collection<ExpressionTreeNode> getChildren() {
		return myChildren;
	}
	/**
	 * Sets the value of node once it has been executed (nodes holding constants are unchanged)
	 * @param parent
	 */
	public void setValue(double value){
		mValue = value;
	}
	
	/**
	 * Gets the value stored in the node
	 */
	public double getValue(){
		return mValue;
	}
	/**
	 * Gets the input object stored in the node
	 */
	public Input getInput() {
		return mInput;
	}
	/**
	 * Sets the status of the node to reflect whether it has been called by a command
	 */
	public void setExecuted() {
		mExecuted = true;
	}
}
