package back_end;
import java.util.List;
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
	public void initTree(List<Input> myExpressions){
      //  ExpressionTreeNode node;
      //  constructTree(myExpressions,0);

}
	/**
	 * NOTE TO SELF- LAMBDA EXPRESSIONS/MAP???
	 */
		private ExpressionTreeNode constructTree(List<Input> myInput) throws ClassNotFoundException{
			ExpressionTreeNode root = new ExpressionTreeNode();
			ExpressionTreeNode current = new ExpressionTreeNode();
			ExpressionTreeNode parent= new ExpressionTreeNode();
			for(Input input:myInput){
				if(root==null){
					root = new ExpressionTreeNode(input,null);
					
				}
				else if(input.getType().equals("ListStart")){
					current = new ExpressionTreeNode(input,parent);
					current = parent;
				}
				else if(input.getType().equals("ListEnd")){
					parent = parent.getParent();
				}
				else{
					current = new ExpressionTreeNode(input,parent);
				}
			}
			return root;
		}
		
		private void traverseTree(){
			        Stack<ExpressionTreeNode> processor = new Stack<ExpressionTreeNode>();
			        ArrayList<TreeNode> data = new ArrayList<TreeNode>();
			        processor.push(root);
			        while(!processor.isEmpty()){
			            TreeNode temp = processor.pop();
			            if(!temp.equals(null)){
			                data.add(temp);
			                
			            }
			            System.out.println(temp.neighbors);
			            for(TreeNode tn: temp.getNeighbors()){
			                processor.push(tn);
			            }
			            
			        }
			        return data;
			    }
		}
		
		}
