package back_end.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import back_end.model.Input;
import back_end.model.ProgramParser;
import back_end.commands.CustomCommand;
import back_end.commands.constant.Constant;
import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.CommandLibrary;
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

	public ExpressionTree(String lang) {
		this.currentLanguage = lang;
		this.initTree();
	}
	public ExpressionTree(ExpressionTreeNode node, String lang)
	{
		this(lang);
		mRootNode = node;
	}
	public String getLanguage()
	{
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
	
	public HashMap<String, CustomCommand> getCustomCommandContainer()
	{
		return mCommandLib.getCustomCommandContainer();
	}

	public void initTree() {
		mInputs = new ArrayList<>();
		mRootNode = null;
		mParser = new ProgramParser();
		mParser.addPatterns(SYNTAX);
		mCommandLib = new CommandLibrary(this.currentLanguage);
	}

	public ExpressionTreeNode constructTree(String commandString) throws UnrecognizedCommandException {
		Scanner cScanner = new Scanner(commandString);
		// Parse string into inputs, getting rid of Comment type
		while (cScanner.hasNext()) {
			String in = cScanner.next().trim().toLowerCase();
			String type = mParser.getSymbol(in);
			System.out.println("Type: " + type);
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
			mRootNode = new ExpressionTreeNode(this.currentLanguage, rootInput, null);
			ExpressionTreeNode currentNode = mRootNode;
			for (Input input : inputs) {
				System.out.println("Input: " + input.getParameter());
				currentNode = buildBranch(input, currentNode);
				System.out.println("Parent: " + currentNode.getParent().getInput().getParameter());
			}
			return mRootNode;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private ExpressionTreeNode buildBranch(Input input, ExpressionTreeNode currNode)
			throws CommandException {
		ExpressionTreeNode inputNode = new ExpressionTreeNode(this.currentLanguage, input, null);
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
		currNode = checkFinishedList(currNode, inputNode);
		inputNode.setParent(currNode);
		currNode.getChildren().add(inputNode);
		currNode = inputNode;
		return currNode;
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
	
	private boolean isChildrenFull(ExpressionTreeNode node) throws UnrecognizedCommandException {
		return node != mRootNode && (isConstant(node.getInput()) || (isVariable(node.getInput()))
				|| (isCommand(node.getInput())&&mCommandLib.getNumParam(node.getInput().getParameter()) == node.getChildren().size()));
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
	 * @return 
	 * @throws VariableNotFoundException 
	 * @throws CommandException 
	 * @throws Exception
	 */
	


	private double createFinalOutput(ExpressionTreeNode myRoot) {
		double output = -1;
		for (ExpressionTreeNode child : myRoot.getChildren()){
			output = (double) child.getOxygen().getReturnValue();
		}
		return output;
	}

	public void traverseKid(ExpressionTreeNode node, Model state)
			throws VariableNotFoundException, CommandException {
		if (node == null){
			return;}
		checkListStart(state, node);
		Input currInput = node.getInput();
		String nodeName = currInput.getParameter();
		if (isConstant(currInput)) {
			return;
		} 
		else if(isVariable(currInput)) { 
			if (isVariableStored(currInput, state)) { // Turn a variable into a constant directly
				Double value = state.mVariableLibrary.retrieveVariable(nodeName);
				Oxygen<Double> constantOxy = new Oxygen<>(this.currentLanguage, Constant.CONSTANT_TYPE);
				constantOxy.convertLight(value.toString());
				constantOxy.putReturnValue(value);
				constantOxy.putSubContent(nodeName); // In case we are redefining the same variable again
				node.setOxygen(constantOxy);
			}
			return;
		} else if (isCommand(currInput)) {
			libraryLookUp(node, state);
		}
	}
	
	private void nodeExecute(ExpressionTreeNode node, Model state, CommandInterface command)
			throws CommandException, VariableNotFoundException {
		Double value = command.Execute(state);	
		node.getOxygen().putReturnValue(value);
	}
	
	private void libraryLookUp(ExpressionTreeNode node, Model state) throws CommandException, VariableNotFoundException{
		CommandInterface command = null;
		try{
			 command = mCommandLib.getCommand(node.getInput().getParameter());
		}
		catch(CommandException e){
			if(state.getCustomCommands().containsKey(node.getInput().getParameter())){
				command = state.getCustomCommands().get(node.getInput().getParameter());	
			} else {
				throw new UnrecognizedCommandException(node.getInput().getParameter());
			}
		}
		ExpressionTree [] arr = new ExpressionTree[1];
		arr[0]=new ExpressionTree(node,this.getLanguage());
		command.setParameters(state, arr);
		nodeExecute(node, state, command);
	}
		
	private void checkListStart(Model state, ExpressionTreeNode node) throws CommandException, VariableNotFoundException {
		if (isListStart(node.getInput())) {
			for (ExpressionTreeNode kid : node.getChildren()) {
				traverseKid(kid, state);
			}
			Double value = createFinalOutput(node);	
			node.getOxygen().putReturnValue(value);
		}
	}
	
	/*public static void main(String[] args) {
		ExpressionTree test = new ExpressionTree("English");
		String s = "MAKE :X 3";
		String d = "FD :X";
		try {
			test.constructTree(s);
			Model model = new Model();
			test.traverse(model);
			
			System.out.println("Try Variable: ");
			
			test = new ExpressionTree("English");
			test.constructTree(d);
			test.traverse(model);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}*/

}