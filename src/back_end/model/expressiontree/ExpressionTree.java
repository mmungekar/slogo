package back_end.model.expressiontree;

import java.util.ArrayList;
import java.util.List;

import back_end.commands.constant.Constant;
import back_end.commands.custom.CustomCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.UnrecognizedTypeException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.CommandFactory;
import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.NodeFactory;
import back_end.model.container.Input;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;

/**
 * Main language parsing structure, construct all commands and constants in a
 * tree structure. Main steps are as described below. 1. Input the ENTIRE
 * command string and parse it into Inputs 2. Construct the whole tree by
 * storing blank commands and constant values in all the nodes 3. When
 * traversing the tree in a bottom up fashion, we execute the command and pass
 * the returned value up to the parent on the fly.
 */
public class ExpressionTree {

	private TreeNode mRootNode;
	private NodeFactory mNodeFactory;

	private CustomCommandLibrary mCustomCommandLib;
	private String currentLanguage;

	public ExpressionTree(String lang) {
		this.currentLanguage = lang;
		this.initTree();
		mCustomCommandLib = new CustomCommandLibrary();
		mNodeFactory = new NodeFactory(lang);
	}

	public ExpressionTree(TreeNode node, String lang, CustomCommandLibrary customLib) throws UnrecognizedTypeException {
		this(lang);
		mRootNode = mNodeFactory.clone(node);
		mCustomCommandLib = customLib;
	}

	public String getLanguage() {
		return currentLanguage;
	}

	public TreeNode getRootNode() {
		return this.mRootNode;
	}

	public double traverse(Model ms) throws CommandException, VariableNotFoundException {
		// Execute each commands in order
		double retVal = 0;
		for (TreeNode childrenNode : mRootNode.getChildren()) {
			retVal = traverseKid(childrenNode, ms);
		}
		return retVal;
	}

	public void initTree() {
		mRootNode = null;
	}

	// Turn a full list of commands into a sub tree
	public TreeNode constructTree(List<Input> inputs) {
		if (inputs.size() == 0)
			return null;
		try {
			Input rootInput = new Input(Constant.ROOT_TYPE, Constant.ROOT_TYPE);
			mRootNode = mNodeFactory.getTreeNode(rootInput);
			TreeNode currentNode = mRootNode;
			for (Input input : inputs) {
				System.out.println();
				TreeNode inputNode = mNodeFactory.getTreeNode(input);
				System.out.println("Input: " + inputNode.getName());
				currentNode = addLeaf(inputNode, currentNode);
				if(currentNode.getParent() != null) System.out.println("Parent: " + currentNode.getParent().getName());
				System.out.println();
			}
			return mRootNode;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Add the input node to the closest available spot to the current node,
	 * return the newly appended node
	 * 
	 * @param inputNode
	 * @param currNode
	 * @return
	 * @throws CommandException
	 */
	private TreeNode addLeaf(TreeNode inputNode, TreeNode currNode) throws CommandException {
		while (currNode.isChildrenFull(mCustomCommandLib)) {
			if (currNode == mRootNode)
				return mRootNode;
			currNode = currNode.getParent();
		}
		// currNode = checkFinishedList(currNode, inputNode);
		currNode = inputNode.appendTo(currNode);
		return currNode;
	}

	public double traverseKid(TreeNode node, Model state) throws VariableNotFoundException, CommandException {
		if (node == null) {
			return 0;
		}
		return node.traverse(state);
	}

	/*
	 * public static void main(String[] args) { ExpressionTree test = new
	 * ExpressionTree("English"); String s = "MAKE :X 3"; String d = "FD :X";
	 * try { test.constructTree(s); Model model = new Model();
	 * test.traverse(model);
	 * 
	 * System.out.println("Try Variable: ");
	 * 
	 * test = new ExpressionTree("English"); test.constructTree(d);
	 * test.traverse(model);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}