package back_end.commands.presetLibrary;

import java.util.ArrayList;
import java.util.Iterator;

import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.commands.CustomCommand;
import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.ExpressionTreeNode;

public class To implements CommandInterface<Oxygen<ExpressionTree>>
{	
	private ExpressionTree mainTree;
	
	public void setParameters(Oxygen<ExpressionTree>... data) throws NotEnoughParameterException
	{
		mainTree = data[0].getContent();
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
			model.addCustomCommand(mainTree.getRootNode().getInput().getParameter(),
					new CustomCommand(variableNames, new ExpressionTree(iterator.next(), model.getCurrentLanguage())));
		}
		catch (Exception e)
		{
			return 0;
		}
		return 1;
	}
}
