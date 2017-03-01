package back_end;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import back_end.commands.CommandInterface;
import back_end.constant.Constant;

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

	public ExpressionTree(ExpressionTreeNode node, String lang){
		super();
		mRootNode = node;
	}
	
	public void initTree(String language) {
		mInputs = new ArrayList<>();
		mRootNode = null;
		mParser = new ProgramParser();
		mParser.addPatterns(SYNTAX);
		mCommandLib = new CommandLibrary();
		mCommandLib.buildLib(language);
	}

	public ExpressionTreeNode constructTree(String commandString) throws UnrecognizedCommandException {
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

	// Turn a full list of commands into a sub tree
	private ExpressionTreeNode constructBranch(List<Input> inputs) throws UnrecognizedCommandException {
		if (inputs.size() == 0){
			return null;
		}
			Input rootInput = new Input(null, Constant.ROOT_TYPE);
			mRootNode = new ExpressionTreeNode(rootInput, null);
			ExpressionTreeNode currentNode = mRootNode;
			for (Input input : inputs) {
				System.out.println("Input: " + input.getParameter());
				currentNode = initNode(input, currentNode);
				System.out.println("Parent: " + currentNode.getParent().getInput().getParameter());
				System.out.println();
			}
			return mRootNode;
	}

	private ExpressionTreeNode initNode(Input input, ExpressionTreeNode currNode)
			throws UnrecognizedCommandException {
		ExpressionTreeNode inputNode = new ExpressionTreeNode(input, null);
		currNode = findAvailableParent(currNode,inputNode);
		inputNode.setParent(currNode);
		currNode.getChildren().add(inputNode);
		currNode = inputNode;
		return currNode;
	}

	private ExpressionTreeNode findAvailableParent(ExpressionTreeNode currNode, ExpressionTreeNode inputNode) throws UnrecognizedCommandException {
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
		return currNode;
	}

	private boolean isChildrenFull(ExpressionTreeNode node) throws UnrecognizedCommandException {
		return node != mRootNode && (isConstant(node.getInput())
				|| mCommandLib.getNumParam(node.getInput().getParameter()) == node.getChildren().size());
	}

	private boolean isCommand(Input i) {
		return i.getType().equals(Constant.COMMAND_TYPE);
	}

	private boolean isConstant(Input i) {
		return i.getType().equals(Constant.CONSTANT_TYPE);
	}
	

	/**
	 * Given an already-built ExpressionTree, we traverse it using depth first
	 * search and execute all the possible commands as we go.
	 * 
	 * @param ModelState
	 *            (Should be the static state from the canvas)
	 * @throws UnrecognizedCommandException
	 * @throws NotEnoughParameterException
	 * @throws Exception
	 */
	public String traverse(Model ms) throws NotEnoughParameterException, UnrecognizedCommandException {
		// Execute each commands in order
		for (ExpressionTreeNode childrenNode : mRootNode.getChildren()) {
			traverseChild(childrenNode, ms);
		}
		String finalOutput = "";
		for (ExpressionTreeNode child : mRootNode.getChildren()){
			finalOutput = finalOutput + Double.toString(child.getValue()) + " ";
		}
		return finalOutput;
	}

	private void traverseChild(ExpressionTreeNode node, Model state)
			throws NotEnoughParameterException, UnrecognizedCommandException {
		if (node == null)
			return;
		Input currInput = node.getInput();
		if (isConstant(currInput)) {
			node.setExecuted();
			return;
		} else if (isCommand(currInput)) {
			execCommand(node, state, currInput);
		}
	}

	private void execCommand(ExpressionTreeNode node, Model state, Input currInput)
			throws NotEnoughParameterException, UnrecognizedCommandException {
		checkIfStatement(node, state, currInput);
		for (ExpressionTreeNode kid : node.getChildren()) {
			traverseChild(kid, state);
		}
		CommandInterface command = setParams(node, currInput);
		double value = command.Execute(state);
		node.setExecuted();
		node.setValue(value);
	}

	private CommandInterface setParams(ExpressionTreeNode node, Input currInput)
			throws UnrecognizedCommandException, NotEnoughParameterException {
		int paramNum = mCommandLib.getNumParam(currInput.getParameter());
		if (paramNum != node.getChildren().size()){
			throw new NotEnoughParameterException(currInput.getParameter());
		}
		CommandInterface command = mCommandLib.getCommand(currInput.getParameter());
		double[] params = new double[paramNum]; 
		Iterator<ExpressionTreeNode> iter = node.getChildren().iterator();
		for (int i = 0; i < params.length; i++) {
			params[i] = iter.next().getValue();
		}
		command.setParameters(params);
		return command;
	}

	private void checkIfStatement(ExpressionTreeNode node,Model state, Input myInput) throws NotEnoughParameterException, UnrecognizedCommandException{
		if(myInput.getParameter()==Constant.IF_COMMAND_TYPE){
			Iterator<ExpressionTreeNode> iter = node.getChildren().iterator();
			ExpressionTreeNode child = iter.next();
			traverseChild(child,state);
			if(child.getValue()!=0){
				traverseChild(iter.next(),state);
			}
			else{
				iter.next();
				traverseChild(iter.next(),state);
			}
		}
	}
	
	public static void main(String[] args) {
		ExpressionTree test = new ExpressionTree("english");
		String s = "SUM GREATER? 6 3 AND 2 1";
		try {
			ExpressionTreeNode root = test.constructTree(s);
			test.traverse(null);
			for (ExpressionTreeNode node : root.getChildren()) {
				System.out.println("Greater? " + node.getValue());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
