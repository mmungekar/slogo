package back_end.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import back_end.Model;
import back_end.commands.constant.Constant;
import back_end.commands.presetLibrary.CommandInterface;
import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.UnrecognizedCommandException;

/**
 * Main language parsing structure, construct all commands and constants in a
 * tree structure. Main steps are as described below. 1. Input the ENTIRE
 * command string and parse it into Inputs 2. Construct the whole tree by
 * storing blank commands and constant values in all the nodes 3. When
 * traversing the tree in a bottom up fashion, we execute the command and pass
 * the returned value up to the parent on the fly.
 */
public class ExpressionTree {
	private static final String SYNTAX = "resources/languages/Syntax";
	private List<Input> mInputs;
	private ExpressionTreeNode mRootNode;
	private ProgramParser mParser;
	private CommandLibrary mCommandLib;
	private String currentLanguage;

	/**
	 * The two constructors for the tree require an input for language in order
	 * to initialize the command library The latter constructor can be used to
	 * extract subtrees from the larger tree (i.e., by taking out a node and its
	 * children)
	 * 
	 * @param lang
	 *            (String that defines which language is being used)
	 */
	public ExpressionTree(String lang) {
		this.currentLanguage = lang;
		initTree(lang);
	}

	public ExpressionTree(ExpressionTreeNode node, String lang) {
		super();
		mRootNode = node;
	}

	private void initTree(String language) {
		mInputs = new ArrayList<>();
		mRootNode = null;
		mParser = new ProgramParser();
		mParser.addPatterns(SYNTAX);
		mCommandLib = new CommandLibrary(this.currentLanguage);
		// mCommandLib.buildLib(language);
	}

	/**
	 * Construct tree from user-entered text
	 * 
	 * @param commandString
	 */
	public ExpressionTreeNode constructTree(String commandString) throws CommandException {
		Scanner cScanner = new Scanner(commandString);
		// Parse string into inputs, getting rid of Comment type
		while (cScanner.hasNext()) {
			String in = cScanner.next().trim().toLowerCase();
			String type = mParser.getSymbol(in);
			if (type.equals(Constant.COMMENT_TYPE)) {
				cScanner.nextLine();
				continue;
			}
			Input input = new Input(in, type);
			mInputs.add(input);
		}
		cScanner.close();
		return constructBranch(mInputs);
	}

	/**
	 * For every word the user inputs, a node is created and added to the tree
	 * 
	 * @param inputs
	 *            is a list of inputs returned by the parser
	 */
	private ExpressionTreeNode constructBranch(List<Input> inputs) throws CommandException {
		if (inputs.size() == 0) {
			return null;
		}
		Input rootInput = new Input(null, Constant.ROOT_TYPE);
		mRootNode = new ExpressionTreeNode(this.currentLanguage, rootInput, null);
		ExpressionTreeNode currentNode = mRootNode;
		for (Input input : inputs) {
			System.out.print("Input: " + input.getParameter());
			currentNode = initNode(input, currentNode);
			System.out.println(" (Parent: " + currentNode.getParent().getInput().getParameter() + ")");
		}
		return mRootNode;
	}

	private ExpressionTreeNode initNode(Input input, ExpressionTreeNode currNode) throws CommandException {
		ExpressionTreeNode inputNode = new ExpressionTreeNode(this.currentLanguage, input, null);
		currNode = setAvailableParent(inputNode, currNode);
		return currNode;
	}

	/**
	 * This method checks to see which parent nodes have available space for
	 * children and which parent nodes (commands) already have a maximum number
	 * of parameters
	 * 
	 * @param currNode
	 *            (the to-be parent node)
	 * @param inputNode
	 */
	private ExpressionTreeNode setAvailableParent(ExpressionTreeNode inputNode, ExpressionTreeNode currNode)
			throws UnrecognizedCommandException {
		while (isChildrenFull(currNode)) {
			if (currNode == mRootNode) {
				return mRootNode;
			}
			currNode = currNode.getParent();
		}
		currNode = checkFinishedList(currNode, inputNode);
		return currNode;
	}

	private boolean isChildrenFull(ExpressionTreeNode node) throws UnrecognizedCommandException {
		return node != mRootNode && (isConstant(node.getInput()) || (isCommand(node.getInput())
				&& mCommandLib.getNumParam(node.getInput().getParameter()) == node.getChildren().size()));
	}

	private ExpressionTreeNode checkFinishedList(ExpressionTreeNode currNode, ExpressionTreeNode inputNode) {
		if (isListEnd(inputNode.getInput())) {
			currNode = currNode.getParent();
			return currNode;
		}
		inputNode.setParent(currNode);
		currNode.getChildren().add(inputNode);
		currNode = inputNode;
		return currNode;
	}

	/**
	 * Returns the type of an input
	 * 
	 * @param i
	 *            refers to an input
	 */
	private boolean isCommand(Input i) {
		return i.getType().equals(Constant.COMMAND_TYPE);
	}

	private boolean isConstant(Input i) {
		return i.getType().equals(Constant.CONSTANT_TYPE);
	}

	private boolean isListStart(Input i) {
		return i.getType().equals(Constant.LISTSTART_TYPE);
	}

	private boolean isListEnd(Input i) {
		return i.getType().equals(Constant.LISTEND_TYPE);
	}

	/**
	 * Given an already-built ExpressionTree, we traverse it using depth first
	 * search and execute all the possible commands as we go.
	 * 
	 * @param ModelState
	 *            (Should be the static state from the canvas)
	 * @throws CommandException
	 * @throws Exception
	 */
	public String traverse(Model ms) throws CommandException {
		// Execute each commands in order
		for (ExpressionTreeNode childrenNode : mRootNode.getChildren()) {
			traverseChild(childrenNode, ms);
		}
		return createFinalOutput();
	}

	private String createFinalOutput() {
		return mRootNode.getChildren().stream().map(n -> Double.toString(n.getValue()))
				.collect(Collectors.joining(" "));
	}

	private void traverseChild(ExpressionTreeNode node, Model state) throws CommandException {
		if (node == null)
			return;
		checkListStart(state, node);
		if (isConstant(node.getInput())) {
			node.setExecuted();
			return;
		} else if (isCommand(node.getInput())) {
			execCommand(node, state);
		}
	}

	/**
	 * Checks to see if the child is the start of the list-- if so, execute
	 * everything in the list
	 * 
	 * @throws CommandException
	 */
	private void checkListStart(Model state, ExpressionTreeNode node) throws CommandException {
		if (isListStart(node.getInput())) {
			for (ExpressionTreeNode kid : node.getChildren()) {
				traverseChild(kid, state);
			}
			node.setExecuted();
		}
	}

	/**
	 * Instantiates commands (if the current node stores a command), adds the
	 * value of the children nodes as parameters, and executes
	 * 
	 * @param node
	 * @param state
	 * @param currInput
	 * @throws CommandException
	 */
	private void execCommand(ExpressionTreeNode node, Model state) throws CommandException {
		for (ExpressionTreeNode kid : node.getChildren()) {
			traverseChild(kid, state);
		}
		CommandInterface command = setParams(node);
		double value = command.Execute(state);
		node.setExecuted();
		node.setValue(value);
	}

	private CommandInterface setParams(ExpressionTreeNode node) throws CommandException {
		int paramNum = mCommandLib.getNumParam(node.getInput().getParameter());
		if (paramNum != node.getChildren().size()) {
			throw new NotEnoughParameterException(node.getInput().getParameter(), paramNum, node.getChildren().size());
		}
		CommandInterface command = mCommandLib.getCommand(node.getInput().getParameter());
		double[] params = new double[paramNum];
		Iterator<ExpressionTreeNode> iter = node.getChildren().iterator();
		for (int i = 0; i < params.length; i++) {
			params[i] = iter.next().getValue();
			// System.out.println(params[i]);
		}
		command.setParameters(params);
		return command;
	}

	/**
	 * TO BE MOVED TO THE IF-STATEMENT CLASS
	 * 
	 * @throws CommandException
	 */
	private void checkIfStatement(ExpressionTreeNode node, Model state) throws CommandException {
		if (node.getInput().getParameter().equals(Constant.IF_COMMAND_TYPE)) {
			Iterator<ExpressionTreeNode> iter = node.getChildren().iterator();
			ExpressionTreeNode child = iter.next();
			traverseChild(child, state);
			if (child.getValue() != 0) {
				traverseChild(iter.next(), state);
			} else {
				iter.next();
				traverseChild(iter.next(), state);
			}
			node.setExecuted();
		}
	}

	public void clean() throws CommandException {
		Input rootInput = new Input(null, Constant.ROOT_TYPE);
		mRootNode = new ExpressionTreeNode(this.currentLanguage, rootInput, null);
	}

	/*
	 * public static void main(String[] args) { ExpressionTree test = new
	 * ExpressionTree("english"); String s = "SUM GREATER? 6 3 AND 2 1"; try {
	 * ExpressionTreeNode root = test.constructTree(s); test.traverse(null); for
	 * (ExpressionTreeNode node : root.getChildren()) {
	 * System.out.println("Greater? " + node.getValue()); }
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}