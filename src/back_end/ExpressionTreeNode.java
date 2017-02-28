package back_end;
<<<<<<< HEAD
import java.lang.reflect.InvocationTargetException;
=======
	
>>>>>>> branch 'master' of git@coursework.cs.duke.edu:CompSci308_2017Spring/slogo_team16.git
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
<<<<<<< HEAD
import commands.CommandInterface;
=======

import back_end.commands.CommandInterface;
import back_end.constant.Constant;

>>>>>>> branch 'master' of git@coursework.cs.duke.edu:CompSci308_2017Spring/slogo_team16.git
/**
 * Constructs the nodes of the expression tree, which will contain either
 * commands, operators, or integer values (hence, type Object)
 */
public class ExpressionTreeNode {
	private CommandLibrary mCommandLib;
	// Contents
	private Input mInput;
	private CommandInterface mCommand;
	private double mValue;
	private ExpressionTreeNode myParent;
	private List<ExpressionTreeNode> myChildren;
<<<<<<< HEAD
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
=======
	private boolean mExecuted;

>>>>>>> branch 'master' of git@coursework.cs.duke.edu:CompSci308_2017Spring/slogo_team16.git
	/**
	 * If the parent node is specified, it is included in the constructor
	 * 
	 * @param x
	 *            specifies the contents of the node; parent specifies the
	 *            parent node which operates upon the current node
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws UnrecognizedCommandException 
	 */
	public ExpressionTreeNode(Input x, ExpressionTreeNode parent)
			throws UnrecognizedCommandException {
		myChildren = new ArrayList<ExpressionTreeNode>();
		mCommandLib = new CommandLibrary();
		mCommand = null;
		mExecuted = false;
		myParent = parent;
		mInput = x;
		switch (x.getType()) {
		case Constant.ROOT_TYPE:
			break;
		case Constant.CONSTANT_TYPE:
			mValue = Double.parseDouble(x.getParameter());
			break;
		case Constant.COMMAND_TYPE:
			mCommand = mCommandLib.getCommand(x.getParameter());
			break;
		}
	}
<<<<<<< HEAD
	private void addChild(ExpressionTreeNode child) {
=======

	public void addChild(ExpressionTreeNode child) {
>>>>>>> branch 'master' of git@coursework.cs.duke.edu:CompSci308_2017Spring/slogo_team16.git
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
<<<<<<< HEAD
	/**
	 * Returns the content of the current node
	 * 
	 * @return the double/string/command the node is storing
	 */
=======
	
	public void setParent(ExpressionTreeNode parent){
		myParent = parent;
	}

	public Collection<ExpressionTreeNode> getChildren() {
		return myChildren;
	}
	
	public void setValue(double value){
		mValue = value;
	}
	
	public double getValue(){
		return mValue;
	}

>>>>>>> branch 'master' of git@coursework.cs.duke.edu:CompSci308_2017Spring/slogo_team16.git
	public Input getInput() {
		return mInput;
	}
<<<<<<< HEAD
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
=======

	public void setExecuted() {
		mExecuted = true;
>>>>>>> branch 'master' of git@coursework.cs.duke.edu:CompSci308_2017Spring/slogo_team16.git
	}
}