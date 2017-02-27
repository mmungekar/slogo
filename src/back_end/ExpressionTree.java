package back_end;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import commands.CommandInterface;

/**
 * Constructs the expression tree which is formed after parsing commands
 */
public class ExpressionTree {
	/**
	 * The constructor takes in a list of input objects and forms a tree
	 * @param myInput is a list of inputs
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public ExpressionTree (List<Input> myInput) throws ClassNotFoundException, NoSuchMethodException, 
	SecurityException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, 
	InstantiationException {
		constructTree(myInput);
		
	}
	
	private ExpressionTreeNode root = new ExpressionTreeNode();
	private ExpressionTreeNode current = new ExpressionTreeNode();
	private ExpressionTreeNode parent = root;
	
	/**
	 *Constructs tree based on types of inputs are given to it
	 */
	private ExpressionTreeNode constructTree(List<Input> myInput) throws ClassNotFoundException, NoSuchMethodException, 
																		SecurityException, IllegalAccessException, 
																		IllegalArgumentException, InvocationTargetException, 
																		InstantiationException {
		for (Input input : myInput) {
			checkParamCount(parent);
			checkListStart(current, parent, input);
			checkListEnd(current, parent, input);
			checkCommand(input, current, parent);
		}
		return root;
	}
	
	private void checkListEnd(ExpressionTreeNode current, ExpressionTreeNode parent, Input input) {
		if (input.getType().equals("ListEnd")) {
			parent = parent.getParent();
		}
	}

	private void checkListStart(ExpressionTreeNode current, ExpressionTreeNode parent, Input input) {
		if (input.getType().equals("ListStart")){
			current = parent;
		}
	}
/**
 * If a command input is given, create a new node and designate it as a parent node
 */
	private void checkCommand(Input input, ExpressionTreeNode current, ExpressionTreeNode parent) throws ClassNotFoundException, 
																										NoSuchMethodException,
																										SecurityException, 
																										IllegalAccessException,
																										IllegalArgumentException,
																										InvocationTargetException,
																										InstantiationException{
		if(input.getType().equals("Command")){
			current = new ExpressionTreeNode(input, parent);
			current = parent;
		}
		else {
			current = new ExpressionTreeNode(input, parent);
		}
	}
	/**
	 * If the parent node is a command node, and it has its maximum number of parameters, the tree goes up levels
	 * until it find a parent node which has not fulfilled its needed parameter count
	 */
	private void checkParamCount(ExpressionTreeNode parent) throws NoSuchMethodException, SecurityException{
		if(parent.getInput().getType().equals("Command") &&
				parent.getRequiredChildNumber()==parent.getChildren().size()){
				parent = parent.getParent();
				checkParamCount(parent);
		}
		return;
	}
	
	
/*	private void traverseTree() throws NoSuchMethodException, SecurityException {
		Stack<ExpressionTreeNode> processor = new Stack<ExpressionTreeNode>();
		processor.push(root);
		ExpressionTreeNode temp = root;
		while(temp.getChildren().size>0)){
		for(ExpressionTreeNode node:temp.getChildren()){
			if(node.getContents() instanceof CommandInterface ){
				java.lang.reflect.Method method = ((Class<?>) node.getContents()).getMethod("setParameters",null);
				//call method.invoke
				//call method.execute and get a return value (let's say it's stored as the value "g")
			//	Input newInput = new Input(g.toString, "Double");
			//ExpressionTreeNode replacementNode = new ExpressionTreeNode(newInput);
			//replacementNode.replace(node);
			}
		}
	}
} */

}
