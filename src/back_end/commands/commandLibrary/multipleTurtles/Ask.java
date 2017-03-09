package back_end.commands.commandLibrary.multipleTurtles;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.commandLibrary.TurtleCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class Ask extends TurtleCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws VariableNotFoundException, CommandException {
		List<Integer> myParamValues = getFirstChild().getChildren().stream().filter(elt -> elt != null)
										.map(elt -> elt.getOxygen().getReturnValue().intValue())
										.collect(Collectors.toList());
		model.tellTemps(myParamValues);
		ExpressionTreeNode secondChild = getIterator().next();
		getTree().traverseKid(secondChild,model);
		model.revertActiveTurtles();
		return secondChild.getOxygen().getReturnValue();
	}
}
