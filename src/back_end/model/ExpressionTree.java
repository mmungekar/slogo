package back_end.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import back_end.Input;
import back_end.ProgramParser;
import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.constant.NotEnoughParameterException;
import back_end.library.CommandLibrary;
import back_end.library.UnrecognizedCommandException;
import back_end.library.VariableNotFoundException;

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

	public ExpressionTree(String lang) {
		initTree(lang);
	}

	private void initTree(String language) {
		mInputs = new ArrayList<>();
		mRootNode = null;
		mParser = new ProgramParser();
		mParser.addPatterns(SYNTAX);
		mCommandLib = new CommandLibrary();
		mCommandLib.buildLib(language);
	}

	/**
	 * Build a ExpressionTree out of the command string read from the terminal,
	 * return the root node
	 * @param commandString
	 * @return root node
	 * @throws UnrecognizedCommandException
	 */
	public ExpressionTreeNode constructTree(String commandString) throws UnrecognizedCommandException {
		Scanner cScanner = new Scanner(commandString);
		// Parse string into inputs, getting rid of Comment type
		while (cScanner.hasNext()) {
			String in = cScanner.next().trim().toLowerCase();
			String type = mParser.getSymbol(in);
			//System.out.println("Type: " + type);
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

	// Turn a full list of commands into a sub tree
	private ExpressionTreeNode constructBranch(List<Input> inputs) {
		if (inputs.size() == 0)
			return null;
		try {
			Input rootInput = new Input(null, Constant.ROOT_TYPE);
			mRootNode = new ExpressionTreeNode(rootInput, null);
			ExpressionTreeNode currentNode = mRootNode;
			System.out.println("Start building the tree.");
			System.out.println();
			for (Input input : inputs) {
				System.out.println("New leaf added to the tree: ");
				System.out.println("New Leaf: " + input.getParameter());
				currentNode = buildBranch(input, currentNode);
				System.out.println("Parent: " + currentNode.getParent().getInput().getParameter());
				System.out.println();
			}
			System.out.println("Finish building the tree.");
			System.out.println();
			System.out.println();
			return mRootNode;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private ExpressionTreeNode buildBranch(Input input, ExpressionTreeNode currNode)
			throws UnrecognizedCommandException {
		ExpressionTreeNode inputNode = new ExpressionTreeNode(input, null);
		if (!isChildrenFull(currNode)) {
			inputNode.setParent(currNode);
			currNode.getChildren().add(inputNode);
			currNode = inputNode;
			return currNode;
		}
		while (isChildrenFull(currNode)) {
			if (currNode == mRootNode)
				return mRootNode;
			currNode = currNode.getParent();
		}
		inputNode.setParent(currNode);
		currNode.getChildren().add(inputNode);
		currNode = inputNode;
		return currNode;
	}

	private boolean isChildrenFull(ExpressionTreeNode node) throws UnrecognizedCommandException {
		return node != mRootNode && (isConstant(node.getInput()) || (isVariable(node.getInput()))
				|| mCommandLib.getNumParam(node.getInput().getParameter()) == node.getChildren().size());
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
		return m.mVariableLibrary.hasVariable(i.getParameter());
	}


	/**
	 * Given an already-built ExpressionTree, we traverse it using depth first
	 * search and execute all the possible commands as we go.
	 * 
	 * @param ModelState
	 *            (Should be the static state from the canvas)
	 * @throws UnrecognizedCommandException
	 * @throws NotEnoughParameterException
	 * @throws VariableNotFoundException 
	 * @throws Exception
	 */
	public void traverse(Model ms) throws NotEnoughParameterException, UnrecognizedCommandException, VariableNotFoundException {
		// Execute each commands in order
		for (ExpressionTreeNode childrenNode : mRootNode.getChildren()) {
			traverseKid(childrenNode, ms);
		}
	}

	private void traverseKid(ExpressionTreeNode node, Model state)
			throws NotEnoughParameterException, UnrecognizedCommandException, VariableNotFoundException {
		if (node == null)
			return;
		Input currInput = node.getInput();
		String nodeName = currInput.getParameter();
		if (isConstant(currInput)) {
			node.setExecuted();
			return;
		} else if (isVariable(currInput)) { 
			if (isVariableStored(currInput, state)) { // Turn a variable into a constant                                     
				Double value = state.mVariableLibrary.retrieveVariable(nodeName);
				Oxygen<String> constantOxy = new Oxygen<>(Constant.CONSTANT_TYPE);
				constantOxy.convertLight(value.toString());
				constantOxy.putSubContent(nodeName); // In case we are redefining the same variable again
				node.setOxygen(constantOxy);
			}
			node.setExecuted();
			return;
		} else if (isCommand(currInput)) {
			for (ExpressionTreeNode kid : node.getChildren()) {
				traverseKid(kid, state);
			}
			int paramNum = mCommandLib.getNumParam(nodeName);
			if (paramNum != node.getChildren().size())
				throw new NotEnoughParameterException(currInput.getParameter());

			// Input the correct type of inputs to the command in the TreeNode
			CommandInterface command = mCommandLib.getCommand(nodeName);
			Oxygen[] params = new Oxygen[paramNum];
			Iterator<ExpressionTreeNode> iter = node.getChildren().iterator();
			for (int i = 0; i < params.length; i++) {
				params[i] = iter.next().getOxygen();
			}
			command.setParameters(params);
			Double value = command.Execute(state);	
			Oxygen<Double> valueOxy = new Oxygen<Double>(Constant.CONSTANT_TYPE);
			valueOxy.convertLight(value.toString());
			node.setOxygen(valueOxy);
			node.setExecuted();
		}
	}

	public static void main(String[] args) {
		ExpressionTree test = new ExpressionTree("english");
		String s = "MAKE :X 3";
		String d = "FD :X";
		try {
			test.constructTree(s);
			Model model = new Model();
			test.traverse(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
