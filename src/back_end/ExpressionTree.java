package back_end;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
	private String currentLanguage;

	/**
	 * The two constructors for the tree require an input for language in order to initialize the command library
	 * The latter constructor can be used to extract subtrees from the larger tree (i.e., by taking out a node and its children)
	 * @param lang (String that defines which language is being used)
	 */
		
	public ExpressionTree(String lang) {
		this.currentLanguage = lang;
		initTree();
	}


	public ExpressionTree(ExpressionTreeNode node, String lang){
		super();
		mRootNode = node;
	}
	

	private void initTree() {
		mInputs = new ArrayList<>();
		mRootNode = null;
		mParser = new ProgramParser();
		mParser.addPatterns(SYNTAX);
		mCommandLib = new CommandLibrary(this.currentLanguage);
	}
/**
 * Construct tree from user-entered text
 * @param commandString
 */
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

	/**
	 * For every word the user inputs, a node is created and added to the tree
	 * @param inputs is a list of inputs returned by the parser
	 */
	private ExpressionTreeNode constructBranch(List<Input> inputs) throws UnrecognizedCommandException {
		if (inputs.size() == 0){
			return null;
		}
			Input rootInput = new Input(null, Constant.ROOT_TYPE);
			mRootNode = new ExpressionTreeNode(this.currentLanguage, rootInput, null);
			ExpressionTreeNode currentNode = mRootNode;
			for (Input input : inputs) {
				System.out.println("Input: " + input.getParameter());
				currentNode = initNode(input, currentNode);
				System.out.println("Parent: " + currentNode.getParent().getInput().getParameter());
			}
			return mRootNode;
	}

	private ExpressionTreeNode initNode(Input input, ExpressionTreeNode currNode)
			throws UnrecognizedCommandException {

		ExpressionTreeNode inputNode = new ExpressionTreeNode(this.currentLanguage, input, null);
		currNode = setAvailableParent(currNode,inputNode);
		return currNode;
	}
/**
 * This method checks to see which parent nodes have available space for children and which parent nodes
 * (commands) already have a maximum number of parameters
 * @param currNode (the to-be parent node)
 * @param inputNode
 */
	private ExpressionTreeNode setAvailableParent(ExpressionTreeNode currNode, ExpressionTreeNode inputNode) throws UnrecognizedCommandException {
		while (isChildrenFull(currNode)) {
			if (currNode == mRootNode){
				return mRootNode;
			}
			currNode = currNode.getParent();
		}
		currNode = checkFinishedList(currNode, inputNode);
		return currNode;
	}

	private boolean isChildrenFull(ExpressionTreeNode node) throws UnrecognizedCommandException {
		return node != mRootNode && (isConstant(node.getInput())
				|| (isCommand(node.getInput())&&mCommandLib.getNumParam(node.getInput().getParameter()) == node.getChildren().size()));
	}
	
	private ExpressionTreeNode checkFinishedList(ExpressionTreeNode currNode, ExpressionTreeNode inputNode){
		if(isListEnd(inputNode.getInput())){
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
 * @param i refers to an input
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
		checkListStart(currInput, state, node);
		if (isConstant(currInput)) {
			node.setExecuted();
			return;} 
		else if (isCommand(currInput)) {
			execCommand(node, state, currInput);
		}
	}
	
	/**
	 * Checks to see if the child is the start of the list-- if so, execute everything in the list
	 */
	private void checkListStart(Input currInput, Model state, ExpressionTreeNode node) throws NotEnoughParameterException, UnrecognizedCommandException{
		if(isListStart(currInput)){
			for (ExpressionTreeNode kid : node.getChildren()) {
				traverseChild(kid, state);
			}
		}
	}
	
/**
 * Instantiates commands (if the current node stores a command), adds the value of the children nodes as 
 * parameters, and executes 
 * @param node
 * @param state
 * @param currInput
 */
	private void execCommand(ExpressionTreeNode node, Model state, Input currInput)
			throws NotEnoughParameterException, UnrecognizedCommandException {
		//checkIfStatement(node, state, currInput);
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
		//	System.out.println(params[i]);
		}
		command.setParameters(params);
		return command;
	}
/**
 * TO BE MOVED TO THE IF-STATEMENT CLASS 
 */
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
			node.setExecuted();
		}
	}
	
	public void clean() throws UnrecognizedCommandException{
		Input rootInput = new Input(null, Constant.ROOT_TYPE);
		mRootNode = new ExpressionTreeNode(this.currentLanguage, rootInput, null);
	}

	/*
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
	*/

}