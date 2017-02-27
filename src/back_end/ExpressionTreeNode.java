package back_end;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import commands.CommandInterface;

/**
 * Constructs the nodes of the expression tree, which will contain either
 * commands, operators, or integer values
 */
public class ExpressionTreeNode {
	private CommandLibrary commandLib = new CommandLibrary();
	private CommandInterface myCommand;
	private Input myInput;
	private double myValue;
	private ExpressionTreeNode myParent;
	private List<ExpressionTreeNode> myChildren;
	private int requiredChildNumber;
	private boolean isExecuted;

	/**
	 * If no parameters are specified in the constructor, the node is
	 * initialized without a parent node or contents
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws LibraryLookUpException
	 * @throws UnrecognizedCommandException
	 */
	public ExpressionTreeNode() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			UnrecognizedCommandException, LibraryLookUpException {
		this(null, null);

	}

	/**
	 * There are multiple ways to construct the node- if the children nodes are
	 * unknown, a lone node with a single value is created
	 * 
	 * @param x
	 *            specifies the contents of the node
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws LibraryLookUpException
	 * @throws UnrecognizedCommandException
	 */
	public ExpressionTreeNode(Input x) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			UnrecognizedCommandException, LibraryLookUpException {
		this(x, null);
	}

	/**
	 * If the parent node is specified, it is included in the constructor
	 * 
	 * @param x
	 *            specifies the contents of the node; parent specifies the
	 *            parent node which operates upon the current node
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws LibraryLookUpException
	 * @throws UnrecognizedCommandException
	 */
	public ExpressionTreeNode(Input x, ExpressionTreeNode parent) throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, UnrecognizedCommandException, LibraryLookUpException {
		requiredChildNumber = -1;
		myInput = x;
		checkContents(x);
		myParent = parent;
		parent.addChild(this);
		myChildren = new ArrayList<ExpressionTreeNode>();
		myCommand = null;
		isExecuted = false;
		myParent = parent;
<<<<<<< HEAD
     }
  
     private void addChild(ExpressionTreeNode child){
    	 if(this!=null){
    	 this.myChildren.add(child);
    	 }
     }
     /**
      * Returns the parent of the current node
      * @return the ExpressionTreeNode which is parent to the current one
      */
     public ExpressionTreeNode getParent(){
    	 return this.myParent;
     }
     /**
      * Returns the content of the current node
      * @return the double/string/command the node is storing
      */
     public Input getInput(){
    	 return this.myInput;
     }
     /**
      * Returns the children of the current node
      * @return the list of children of the current node
      */
     public List<ExpressionTreeNode> getChildren(){
    	 return this.myChildren;
     }
     /**
      * Returns the required number of children of the current node (i.e. for a command)
      * @return the necessary number of children of the current node
      */
     public int getRequiredChildNumber(){
    	 return this.requiredChildNumber;
     }
     /**
      * The next two methods cast an input string to a corresponding object 
      * (can be moved to the Input class) 
      * NOTE TO SELF: THINK ABOUT ALTERNATIVES TO IF-ELSE STATEMENTS BELOW
     * @return 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InstantiationException 
      */
     private void checkContents(Input x) throws ClassNotFoundException, NoSuchMethodException, 
     											SecurityException, IllegalAccessException, 
     											IllegalArgumentException, InvocationTargetException, 
     											InstantiationException{
    	 if(x.getType().equals("Constant")){
    		 myValue = Double.parseDouble(x.getParameter());
    		requiredChildNumber = commandLib.getNumParam(x.getParameter());
    	 }
    	 else if(x.getType().equals("Command")){
    		 myCommand = translateInput(x);
    	 }
     }
          
 /**
  * This method, only called when the Input object contains a command names, casts that string name to a command
  * @param  Input x is the input taken in 
  * @return CommandInterface returns an instance of the command from its name
  * @throws ClassNotFoundException
  * @throws InstantiationException
  * @throws IllegalAccessException
  */
     public CommandInterface translateInput(Input x) throws ClassNotFoundException, InstantiationException, 
     														IllegalAccessException
 	{
 		CommandInterface command = commandLib.getCommand(x.getParameter());
 		return command;
 	}
     
     public void setExecuted() {
 		isExecuted = true;
 	}
 }
=======
	}
>>>>>>> branch 'master' of git@coursework.cs.duke.edu:CompSci308_2017Spring/slogo_team16.git

	private void addChild(ExpressionTreeNode child) {
		if (this != null) {
			this.myChildren.add(child);
		}
	}

	/**
	 * Returns the parent of the current node
	 * 
	 * @return the ExpressionTreeNode which is parent to the current one
	 */
	public ExpressionTreeNode getParent() {
		return this.myParent;
	}

	/**
	 * Returns the content of the current node
	 * 
	 * @return the double/string/command the node is storing
	 */
	public Input getInput() {
		return this.myInput;
	}

	/**
	 * Returns the children of the current node
	 * 
	 * @return the list of children of the current node
	 */
	public List<ExpressionTreeNode> getChildren() {
		return this.myChildren;
	}

	/**
	 * Returns the required number of children of the current node (i.e. for a
	 * command)
	 * 
	 * @return the necessary number of children of the current node
	 */
	public int getRequiredChildNumber() {
		return this.requiredChildNumber;
	}

	/**
	 * The next two methods cast an input string to a corresponding object (can
	 * be moved to the Input class) NOTE TO SELF: THINK ABOUT ALTERNATIVES TO
	 * IF-ELSE STATEMENTS BELOW
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws LibraryLookUpException
	 * @throws UnrecognizedCommandException
	 */
	private void checkContents(Input x) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			UnrecognizedCommandException, LibraryLookUpException {
		if (x.getType().equals("Constant")) {
			myValue = Double.parseDouble(x.getParameter());
			requiredChildNumber = commandLib.getNumParam(x.getParameter());
		} else if (x.getType().equals("Command")) {
			myCommand = translateInput(x);
		}
	}

	/**
	 * This method, only called when the Input object contains a command names,
	 * casts that string name to a command
	 * 
	 * @param Input
	 *            x is the input taken in
	 * @return CommandInterface returns an instance of the command from its name
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws LibraryLookUpException
	 * @throws UnrecognizedCommandException
	 */
	public CommandInterface translateInput(Input x) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnrecognizedCommandException, LibraryLookUpException {
		CommandInterface command = commandLib.getCommand(x.getParameter());
		return command;
	}

	public void setSatisfied() {
		isExecuted = true;
	}
}
