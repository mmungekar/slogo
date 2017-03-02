package back_end.commands;

import java.util.ArrayList;
import java.util.HashMap;

import back_end.model.ExpressionTree;
import back_end.model.ExpressionTreeNode;
import back_end.model.Model;
import back_end.model.Oxygen;
import back_end.interfaces.CommandInterface;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;

public class CustomCommand implements CommandInterface<Oxygen<Double>>
{
	private ArrayList<String> variableNames;
	private HashMap<String, Double> variableValues;
	private ExpressionTree commandTree;
	private Double[] parameters;
	
	
	public CustomCommand(ArrayList<String> variableList, ExpressionTree commandTree)
	{
		variableNames = variableList;
		variableValues = new HashMap<String, Double>();
		this.commandTree = commandTree;
	}
	
	public void setParameters(Oxygen<Double>...ds)
	{
		parameters = new Double[ds.length];
		for(int i = 0; i < ds.length ; i++){
			parameters[i] = ds[i].getContent();
		}
	}

	public double Execute(Model model) throws CommandException, VariableNotFoundException
	{
		for (int i = 0; i < parameters.length; i++)
		{
			variableValues.put(variableNames.get(i), parameters[i]);
		}
		
		replaceVariables(commandTree.getRootNode());
		
		return commandTree.traverse(model);
	
	}

	private void replaceVariables(ExpressionTreeNode node)
	{
		if(node==null){
			return;
		}
		if (variableValues.containsKey(node.getInput().getParameter()))
		{
			Oxygen<Double> test = (Oxygen<Double>) node.getOxygen();
			test.putContent(variableValues.get(node.getInput().getParameter()));
			node.setOxygen(test);
		}
		for(ExpressionTreeNode x: node.getChildren()){
			replaceVariables(x);
		}
	}
}
