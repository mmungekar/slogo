package back_end;
	
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import back_end.constant.Constant;
import commands.CommandInterface;

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
		switch (x.getType()) {
		case Constant.ROOT_TYPE:
			break;
		case Constant.CONSTANT_TYPE:
			mValue = Double.parseDouble(x.getParameter());
			break;
		case Constant.COMMAND_TYPE:
			mCommand = mCommandLib.getCommand(x.getParameter());
			break;
		}
	}

	public void addChild(ExpressionTreeNode child) {
		if (this != null) {
			this.myChildren.add(child);
		}
	}

	/**
	 * Returns the parent of the current node
	 * 
	 * @return the ExpressionTreeNode which is parent to the current one
	 */
	public ExpressionTreeNode getParent() {
		return this.myParent;
	}
	
	public void setParent(ExpressionTreeNode parent){
		myParent = parent;
	}

	public Collection<ExpressionTreeNode> getChildren() {
		return myChildren;
	}
	
	public void setValue(double value){
		mValue = value;
	}
	
	public double getValue(){
		return mValue;
	}

	public Input getInput() {
		return mInput;
	}

	public void setExecuted() {
		mExecuted = true;
	}
}
