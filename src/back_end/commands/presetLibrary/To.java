package back_end.commands.presetLibrary;

import java.util.ArrayList;
import java.util.Iterator;

import back_end.Model;
import back_end.commands.CustomCommand;
import back_end.commands.ExpressionTree;
import back_end.commands.ExpressionTreeNode;

public class To implements CommandInterface
{	
	private ExpressionTree mainTree;
	
	public void setParameters(ExpressionTree mainTree)
	{
		this.mainTree = mainTree;
	}
	
	public double Execute(Model model)
	{
		Iterator<ExpressionTreeNode> iterator = mainTree.getRootNode().getChildren().iterator();
		ArrayList<String> variableNames = new ArrayList<String>();
		
		for (ExpressionTreeNode x : iterator.next().getChildren())
		{
			variableNames.add(x.getInput().getParameter());
		}
		try
		{
			mainTree.getCustomCommandContainer().put(mainTree.getRootNode().getInput().getParameter(),
			new CustomCommand(variableNames,new ExpressionTree(iterator.next(), mainTree.getLanguage())));
		}
		catch (Exception e)
		{
			return 0;
		}
		return 1;
	}
}
