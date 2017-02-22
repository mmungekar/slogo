package back_end;
import java.util.Stack;
/**
 *Constructs the expression tree which is formed after parsing commands
 */
public class ExpressionTree {
	/**
	 * CANNOT CONFIRM THIS IS EXACTLY HOW THE TREE NEEDS TO BE FORMED (I.E. I NEED TO 
	 * TAKE A SECOND LOOK AT HOW COMMANDS ARE STRUCTURED)
	 * THIS ASSUMES PREFIX NOTATION
	 * AND CLEARLY, THIS TREE SHOULD NOT BE BINARY LIKE IT IS NOW
	 */
	public void initTree(String input){
		String[] myExpressions = input.split("");
		//Stack<ExpressionTreeNode> myStack =  new Stack();
        ExpressionTreeNode node;
        constructTree(myExpressions,0);

}
		private ExpressionTreeNode constructTree(String[] myExpressions, int i){
			ExpressionTreeNode node;
	            // make a new node if the expression is an operator or a command
			// will need to create method for this
				if(myExpressions[i].isAnOperator()){
				node = new ExpressionTreeNode(myExpressions[i]);
				i++;
				// then go through the rest of the string to form the command's arguments
				// NEED TO ACCOUNT FOR COMMANDS WITH DIFFERENT NUMBERS OF ARGUMENTS
				node.left = constructTree(myExpressions, i+1);
				node.right = constructTree(myExpressions, i+1);
				}
				else{
					node = new ExpressionTreeNode(myExpressions[i]);
					i++;
				}
				return node;
		}
			
		}
