package back_end.model.expressiontree;

import java.util.ArrayList;
import java.util.List;

import back_end.commands.constant.Constant;
import back_end.commands.custom.CustomCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.CommandFactory;
import back_end.libraries.CustomCommandLibrary;
import back_end.model.container.Input;
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

	private List<Input> mInputs;
	private ExpressionTreeNode mRootNode;

	private CommandFactory mCommandLib;
	private CustomCommandLibrary mCustomCommandLib;
	private String currentLanguage;

	public ExpressionTree(String lang) {
		this.currentLanguage = lang;
		this.initTree();
		mCustomCommandLib = new CustomCommandLibrary();
	}

	public ExpressionTree(ExpressionTreeNode node, String lang, CustomCommandLibrary customLib) {
		this(lang);
		mRootNode = node;
		mCustomCommandLib = customLib;
	}

	public String getLanguage() {
		return currentLanguage;
	}

	public ExpressionTreeNode getRootNode() {
		return this.mRootNode;
	}

	public double traverse(Model ms) throws CommandException, VariableNotFoundException {
		// Execute each commands in order
		for (ExpressionTreeNode childrenNode : mRootNode.getChildren()) {
			traverseKid(childrenNode, ms);
		}
		return createFinalOutput(mRootNode);
	}

	public void initTree() {
		mInputs = new ArrayList<>();
		mRootNode = null;
		mCommandLib = new CommandFactory(this.currentLanguage);
	}

	// Turn a full list of commands into a sub tree
	public ExpressionTreeNode constructTree(List<Input> inputs) {
		if (inputs.size() == 0)
			return null;
		try {
			Input rootInput = new Input(null, Constant.ROOT_TYPE);
			mRootNode = new ExpressionTreeNode(this.currentLanguage, rootInput, null);
			ExpressionTreeNode currentNode = mRootNode;
			for (Input input : inputs) {
				System.out.println();
				System.out.println("Input: " + input.getParameter());
				currentNode = addLeaf(input, currentNode);
			//	System.out.println("Parent: " + currentNode.getParent().getInput().getParameter());
				System.out.println();
			}
			return mRootNode;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private ExpressionTreeNode addLeaf(Input input, ExpressionTreeNode currNode) throws CommandException {
		ExpressionTreeNode inputNode = new ExpressionTreeNode(this.currentLanguage, input, null);
		while (isChildrenFull(currNode)) {
			if (currNode == mRootNode)
				return mRootNode;
			currNode = currNode.getParent();
			System.out.println("Children Full? " + currNode.getInput().getParameter());
		}

		currNode = checkFinishedList(currNode, inputNode);
		return currNode;
	}

	private boolean isChildrenFull(ExpressionTreeNode node) throws UnrecognizedCommandException {

		Input input = node.getInput();
		if (isListStart(input))
			return false;
		if (isCommand(input))
			try {
				return mCommandLib.getNumParam(input.getParameter()) == node.getChildren().size()
						&& !node.getParent().getInput().getType().equals(Constant.GROUPSTART_TYPE);
			} catch (CommandException e) {
				if (mCustomCommandLib.contains(input.getParameter())) {
					System.out.println("Contains " + input.getParameter());
					return mCustomCommandLib.getCustomCommand(input.getParameter()).getNumParams() == node.getChildren()
							.size();
				} else
					return true;
			}
		return node != mRootNode && (isConstant(input) || (isVariable(input))
				|| (isCommand(input) && mCommandLib.getNumParam(input.getParameter()) == node.getChildren().size()));
	}

	private boolean isCommand(Input i) {
		return i.getType().equals(Constant.COMMAND_TYPE);
	}

	private boolean isConstant(Input i) {
		return i.getType().equals(Constant.CONSTANT_TYPE);
	}

	private boolean isVariable(Input i) {
		return i.getType().equals(Constant.VARIABLE_TYPE);
	}

	private boolean isVariableStored(Input i, Model m) {
		return m.getCustomMaster().isVariableStored(i.getParameter());
	}

	private boolean isListStart(Input i) {
		return i.getType().equals(Constant.LISTSTART_TYPE) || i.getType().equals(Constant.GROUPSTART_TYPE);
	}

	private boolean isListEnd(Input i) {
		return i.getType().equals(Constant.LISTEND_TYPE) || i.getType().equals(Constant.GROUPEND_TYPE);
	}

	/**
	 * Given an already-built ExpressionTree, we traverse it using depth first
	 * search and execute all the possible commands as we go.
	 * 
	 * @param ModelState
	 *            (Should be the static state from the canvas)
	 * @return
	 * @throws VariableNotFoundException
	 * @throws CommandException
	 * @throws Exception
	 */
	private double createFinalOutput(ExpressionTreeNode myRoot) {
		double output = -1;
		for (ExpressionTreeNode child : myRoot.getChildren()) {
			output = (double) child.getOxygen().getReturnValue();
		}
		return output;
	}

	public void traverseKid(ExpressionTreeNode node, Model state) throws VariableNotFoundException, CommandException {
		if (node == null) {
			return;
		}
		checkListStart(state, node);

		Input currInput = node.getInput();
		String nodeName = currInput.getParameter();
		if (isConstant(currInput)) {
			return;

		} else if (isVariable(currInput)) {
			if (isVariableStored(currInput, state)) { // Turn a variable into a
														// constant directly
				Double value = 0d;
				value = retriveVariable(state, nodeName);
				Oxygen<Double> constantOxy = new Oxygen<>(this.currentLanguage, Constant.CONSTANT_TYPE);
				constantOxy.convertLight(value.toString());
				constantOxy.putReturnValue(value);
				constantOxy.putSubContent(nodeName); // In case we are
														// redefining the same
														// variable again
				node.setOxygen(constantOxy);
			}
			return;
		} else if (isCommand(currInput)) {

			CommandInterface command = libraryLookUp(node, state);
			nodeExecute(node, state, command);
		}
	}

	private Double retriveVariable(Model state, String nodeName) {
		return state.getCustomMaster().retrieveVariable(nodeName);
	}

	private void nodeExecute(ExpressionTreeNode node, Model state, CommandInterface command)
			throws CommandException, VariableNotFoundException {
		Double value = command.Execute(state);
		node.getOxygen().putReturnValue(value);
		System.out.println(node.getOxygen().getReturnValue());
	}

	private CommandInterface libraryLookUp(ExpressionTreeNode node, Model state)
			throws CommandException, VariableNotFoundException {
		CommandInterface command = null;
		try {
			command = mCommandLib.getCommand(node.getInput().getParameter());
		} catch (CommandException e) {
			if (state.getCustomMaster().getCustomCommandLibrary().containsKey(node.getInput().getParameter())) {
				command = state.getCustomMaster().getCustomCommandLibrary().get(node.getInput().getParameter());
			} else {
				throw new UnrecognizedCommandException(node.getInput().getParameter());
			}
		}
		command.setParameters(state, new ExpressionTree(node, this.getLanguage(), state.getCustomMaster().getCustomCommandLibrary()));
		return command;

	}

	private void checkListStart(Model model, ExpressionTreeNode node)
			throws CommandException, VariableNotFoundException {
		if (isListStart(node.getInput())) {
			for (ExpressionTreeNode kid : node.getChildren()) {
				traverseKid(kid, model);
				System.out.println(kid.getInput().getParameter());
			}
			Double value = createFinalOutput(node);
			node.getOxygen().putReturnValue(value);
		}
	}
	
	private ExpressionTreeNode checkFinishedList(ExpressionTreeNode currNode, ExpressionTreeNode inputNode) {
		if (isListEnd(inputNode.getInput())) {
			currNode = currNode.getParent();
			if(inputNode.getInput().getType().equals(Constant.GROUPEND_TYPE)){
				currNode = currNode.getParent();
			}
			return currNode;
		}
		inputNode.setParent(currNode);
		currNode.getChildren().add(inputNode);
		currNode = inputNode;
		return currNode;
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