package back_end.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import back_end.model.Input;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.libraries.CommandLibrary;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;

/**
 * Constructs the nodes of the expression tree, which will contain either
 * commands, operators, or integer values (hence, type Object)
 */
public class ExpressionTreeNode {
	// Contents
	private Input mInput;
	private Oxygen<?> mOxygen;
	private ExpressionTreeNode myParent;
	private List<ExpressionTreeNode> myChildren;
	private String language;
	
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
	public ExpressionTreeNode(String language, Input x, ExpressionTreeNode parent) throws CommandException {
		myChildren = new ArrayList<ExpressionTreeNode>();
		myParent = parent;
		mInput = x;
		this.language = language;
		mOxygen = new Oxygen<>(language, mInput.getType());
		mOxygen.convertLight(x.getParameter());
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

	public void setParent(ExpressionTreeNode parent) {
		myParent = parent;
	}

	public Collection<ExpressionTreeNode> getChildren() {
		return myChildren;
	}

	public void setOxygen(Oxygen<?> oxygen) {
		mOxygen = oxygen;
	}

	public Oxygen<?> getOxygen() {
		return mOxygen;
	}
	
	public Input getInput(){
		return mInput;
	}

	/**
	 * Creates a copy of the input node and all its children
	 * @param node takes in an input node
	 * @throws UnrecognizedCommandException 
	 */
	public void makeCopy(ExpressionTreeNode node) throws CommandException{
		if(node==null){
			return;
		}
		ExpressionTreeNode newNode = new ExpressionTreeNode(language, node.getInput(),node.getParent());
		for(ExpressionTreeNode child:node.getChildren()){
			makeCopy(child);
		}
	}
}
