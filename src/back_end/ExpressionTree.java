package back_end;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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

	private List<Input> mInputs;
	private ExpressionTreeNode mRootNode;
	private ProgramParser mParser;
	private Interpreter mInterpreter;
	private CommandLibrary mCommandLib;

	public ExpressionTree(String lang) {
		initTree(lang);
	}

	public void initTree(String language) {
		mInputs = new ArrayList<>();
		mRootNode = null;
		mParser = new ProgramParser();
		mInterpreter = new Interpreter();
		mCommandLib = new CommandLibrary();
		mCommandLib.buildLib(language);
	}

	/**
	 * NOTE TO SELF- LAMBDA EXPRESSIONS/MAP???
	 * 
	 * @throws Exception
	 */
	public ExpressionTreeNode constructTree(String commandString) throws Exception {
		Scanner cScanner = new Scanner(commandString);
		// Parse string into inputs, getting rid of Comment type
		while (cScanner.hasNext()) {
			String in = cScanner.next().trim().toLowerCase();
			String type = mInterpreter.getType(in);
			if (type.equals(Constant.COMMENT_TYPE)) {
				cScanner.nextLine();
				continue;
			}
			Input input = new Input(in, type);
			mInputs.add(input);
		}
		cScanner.close();

		/*
		 * ExpressionTreeNode root = new ExpressionTreeNode();
		 * ExpressionTreeNode current = new ExpressionTreeNode();
		 * ExpressionTreeNode parent = new ExpressionTreeNode(); for (Input
		 * input : myInput) { if (root == null) { root = new
		 * ExpressionTreeNode(input, null);
		 * 
		 * } else if (input.getType().equals("ListStart")) { current = new
		 * ExpressionTreeNode(input, parent); current = parent; } else if
		 * (input.getType().equals("ListEnd")) { parent = parent.getParent(); }
		 * else { current = new ExpressionTreeNode(input, parent); } } return
		 * root;
		 */
		return constructBranch(mInputs);
	}

	// Turn a full list of commands into a sub tree
	private ExpressionTreeNode constructBranch(List<Input> inputs) {
		if (inputs.size() == 0)
			return null;
		try {
			int index = 0;
			Input curr = inputs.get(index);
			ExpressionTreeNode root = new ExpressionTreeNode(curr, null);
			if (isCommand(curr)) {
				int numParams = mCommandLib.getNumParam(curr.getParameter());
				System.out.println(numParams);
				while (numParams > 0) {
					numParams--;
					if(index >= inputs.size())
						throw new Exception("Not Enough Inputs.");
					curr = inputs.get(index);
					if (isConstant(curr)) {
						ExpressionTreeNode child = new ExpressionTreeNode(curr, root);
						root.addChild(child);
						index++;
						continue;
					} else {
						List<Input> shortest = getShortestCompleteCommand(index, inputs);
						ExpressionTreeNode child = constructBranch(shortest);
						root.addChild(child);
						index += shortest.size();
						continue;
					}
				}
			}
			return root;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Retrieve the shortest list of input that can make up a complete command
	 * starting at given index.
	 * 
	 * @param index
	 * @param inputs
	 * @return
	 * @throws Exception
	 */
	private List<Input> getShortestCompleteCommand(int index, List<Input> inputs) throws Exception {
		if (inputs.size() == 0)
			return null;
		ArrayList<Input> result = new ArrayList<>();
		Input curr = inputs.get(index);
		if (isConstant(curr)) {
			result.add(curr);
			return result;
		}
		if (isCommand(curr)) {
			int requiredNum = mCommandLib.getNumParam(curr.getParameter());
			while (requiredNum >= 0) {
				index++;
				if (index >= inputs.size())
					throw new Exception("No Complete Command found.");
				curr = inputs.get(index);
				if (isConstant(curr))
					requiredNum --;
				else if (isCommand(curr))
					requiredNum += mCommandLib.getNumParam(curr.getParameter());
				result.add(curr);
			}

		}
		return result;

	}

	private boolean isCommand(Input i) {
		return i.getType().equals(Constant.COMMAND_TYPE);
	}

	private boolean isConstant(Input i) {
		return i.getType().equals(Constant.CONSTANT_TYPE);
	}

	public void traverseTree() {
		
	}

	public static void main(String[] args) {
		ExpressionTree test = new ExpressionTree("english");
		Interpreter inter = new Interpreter();
		String s = "GREATER? 3 6";
		try {
			ExpressionTreeNode root = test.constructTree(s);
			int layer = 0;
			System.out.println("First layer: " + root.getChildren().size());
			for (ExpressionTreeNode node : root.getChildren()) {
				System.out.println("Layer: " + layer + 1);
				System.out.println("Type: " + node.getInput().getType());
				System.out.println("Param: " + node.getInput().getParameter());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
