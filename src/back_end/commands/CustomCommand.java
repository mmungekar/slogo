package back_end.commands;

import java.util.ArrayList;
import java.util.HashMap;

import back_end.Model;
import back_end.commands.presetLibrary.CommandInterface;
import back_end.exceptions.CommandException;

public class CustomCommand implements CommandInterface
{
	private ArrayList<String> variableNames;
	private HashMap<String, Double> variableValues;
	private ExpressionTree commandTree;
	private double[] parameters;
	
	
	public CustomCommand(ArrayList<String> variableList, ExpressionTree commandTree)
	{
		variableNames = variableList;
		variableValues = new HashMap<String, Double>();
		this.commandTree = commandTree;
	}
	
	public void setParameters(double...ds)
	{
		parameters = ds;
	}

	public double Execute(Model model)
	{
		for (int i = 0; i < parameters.length; i++)
		{
			variableValues.put(variableNames.get(i), parameters[i]);
		}
		
		replaceVariables(commandTree.getRootNode());
		try {
			return commandTree.traverse(model);
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return 0;
	}

	private void replaceVariables(ExpressionTreeNode node)
	{
		if(node==null){
			return;
		}
		if (variableValues.containsKey(node.getInput().getParameter()))
		{
			node.setValue(variableValues.get(node.getInput().getParameter()));
		}
		for(ExpressionTreeNode x: node.getChildren()){
			replaceVariables(x);
		}
	}
}
