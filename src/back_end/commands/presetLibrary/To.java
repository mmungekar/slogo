package back_end.commands.presetLibrary;

import java.util.ArrayList;
import java.util.HashMap;

import back_end.commands.CustomCommand;
import back_end.commands.ExpressionTree;
import back_end.commands.ExpressionTreeNode;

public class To
{	
	private ArrayList<String> variableNames;
	private ExpressionTree commandTree;
	private double successVal = 0;
	
	public To ()
	{
		variableNames = new ArrayList<String>();
	}
	public void setParameters(String name, ExpressionTree[] trees, HashMap<String, CustomCommand> map)
	{
		for (ExpressionTreeNode x : trees[0].getRootNode().getChildren())
		{
			variableNames.add(x.getInput().getParameter());
		}
		commandTree = trees[1];
		
		map.put(name, new CustomCommand(variableNames, commandTree));
		successVal = 1;
	}
	
	public double Execute()
	{
		return successVal;
	}
}
