package back_end.libraries;

import java.lang.reflect.Constructor;

import back_end.commands.constant.Constant;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.UnrecognizedTypeException;
import back_end.model.container.Input;
import back_end.model.expressiontree.node.TreeNode;
import back_end.overhead.ProgramParser;

public class NodeFactory {
	private static final String NODE_PATH = "back_end.model.expressiontree.node.";
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";
	private static final String NODE = "Node";
	private String language;
	private ProgramParser mParser;
	
	public NodeFactory(String lang){
		language = lang;
		mParser = new ProgramParser();
		mParser.addPatterns(LANGUAGE_DIRECTORY + this.language);
	}
	
	public TreeNode getTreeNode(Input input) throws UnrecognizedTypeException{
		try {
			String nodeType = NODE_PATH.concat(input.getType()).concat(NODE);
			Class<?> nodeClazz = Class.forName(nodeType);
			Constructor<?> ctor = nodeClazz.getDeclaredConstructor(Input.class);
			TreeNode concreteNode = (TreeNode) ctor.newInstance(input);
			try {
				concreteNode.setName(mParser.getSymbol(input.getParameter()));
			} catch (UnrecognizedCommandException e){
			}
			return concreteNode;
		} catch (Exception e) {
			throw new UnrecognizedTypeException(input.getType()); 
		} 
	}
	
	/**
	 * Creates a copy of the input node and all its children
	 * @param node takes in an input node
	 * @throws UnrecognizedTypeException 
	 * @throws UnrecognizedCommandException 
	 */
	public TreeNode clone(TreeNode node) throws UnrecognizedTypeException{
		if(node==null){
			return null;
		}
		TreeNode cloneNode = this.getTreeNode(node.getInput());
		for(TreeNode cloneChild : node.getChildren()){
			cloneNode.addChild(clone(cloneChild));
		}
		return cloneNode;
		
	}
	
}
