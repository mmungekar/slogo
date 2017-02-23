package back_end;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

/**
 *Constructs the nodes of the expression tree, which will contain either
 *commands, operators, or integer values (hence, type Object) 
 */
public class ExpressionTreeNode {
	 private Object myContents;
     private ExpressionTreeNode myParent;
     private List<ExpressionTreeNode> myChildren;
     private final String CommandMapLocation = "src.resources.languages/CommandMap";
     private ResourceBundle CommandMap = ResourceBundle.getBundle(CommandMapLocation);
     private Map<String,Callable> storeValues; 
 /**
  * There are two ways to construct the node- if the children nodes are unknown, a lone 
  * node with a single value is created
  * @param x specifies the contents of the node
 * @throws ClassNotFoundException 
  */
    public ExpressionTreeNode(){
         myChildren  = new ArrayList<ExpressionTreeNode>();
         myParent = null;
         myContents = null;
     }
     /**
      * If the parent node is specified, it is included in the constructor
      * @param x specifies the contents of the node; parent specifies the parent node which operates
      * upon the current node
     * @throws ClassNotFoundException 
      */
  public ExpressionTreeNode(Input x, ExpressionTreeNode parent) throws ClassNotFoundException{
  	     myParent = parent;
  	     parent.addChild(this);
  	     myContents = checkContents(x);
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
      * The next two methods cast an input string to a corresponding object 
      * (can be moved to the Input class) 
      * NOTE TO SELF: THINK ABOUT ALTERNATIVES TO IF-ELSE STATEMENTS BELOW
      */
     private Object checkContents(Input x) throws ClassNotFoundException{
    	 Object o = new Object();
    	 if(x.getType().equals("Constant")){
    		 o = Double.parseDouble(x.getParameter());
    	 }
    	 else if(x.getType().equals("Command")){
    		 o= getCommand(x.getParameter());
    	 }
    	return o; 
     }
     
     private Class<?> getCommand (String command) throws ClassNotFoundException {
         try{
             String commandPath = CommandMap.getString(command);
             Class<?> myCommand = Class.forName(commandPath);
             return myCommand;
         }
         catch(ClassNotFoundException e){
             return null;
         }
     }
 }


