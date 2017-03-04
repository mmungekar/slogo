package back_end.commands.presetLibrary;

import java.util.Iterator;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.ExpressionTreeNode;
import back_end.model.Model;
import back_end.model.Oxygen;

public class MakeVariable implements CommandInterface<ExpressionTree>{
    private String name;
    private double value;
	@Override
	public void setParameters(Model model, ExpressionTree...os) throws NotEnoughParameterException {
		Iterator<ExpressionTreeNode> iter = os[0].getRootNode().getChildren().iterator();
		ExpressionTreeNode firstChild = iter.next();
		try {
			name = (String) firstChild.getOxygen().getContent();
		} catch (ClassCastException ex) {
			name = (String) firstChild.getOxygen().getSubContent();
		}
		value = (Double) iter.next().getOxygen().getContent();
	}

	@Override
	public double Execute(Model model) {
		model.updateVariable(name, value);
		System.out.println("New variable created: " + name + " : " + value);
		return value;
	}
	

}
