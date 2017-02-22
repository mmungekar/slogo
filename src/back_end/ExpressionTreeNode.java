package back_end;
/**
 *Constructs the nodes of the expression tree, which will contain either
 *commands, operators, or integer values (hence, type Object) 
 */
public class ExpressionTreeNode {
	 Object contents;
     ExpressionTreeNode left;
     ExpressionTreeNode right;
 /**
  * There are two ways to construct the node- if the left and right nodes are unknown, a lone 
  * node with a single value is created
  * @param x specifies the contents of the node
  */
     ExpressionTreeNode(Object x){
         contents = x;
     }
     /**
      * If the left and right nodes must be specified, they are included in the constructor
      * @param x specifies the contents of the node; lNode is the left node; rNode is the right node
      */
     ExpressionTreeNode(Object x, ExpressionTreeNode lNode, ExpressionTreeNode rNode){
         contents = x;
  	     left = lNode;
         right = rNode;
     }
     
 }


