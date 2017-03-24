/**
 * This entire file is part of my code masterpiece
 * 
 * 
 * This file was selected for the masterpiece because it demonstrates the implementation of the MultipleTurtleCommand class.
 * It displays the succinctness of the commands in general, while demonstrating how the Factory design pattern applies
 * to the commands in general.
 */
package back_end.commands.commandLibrary.turtle.multiple;

import java.util.List;
import java.util.stream.Collectors;

import back_end.commands.commandLibrary.MultipleTurtleCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

/**
 * Mina Mungekar
 * For a select group of turtles designated by ID, a group of commands is executed
 */

public class Ask extends MultipleTurtleCommand implements CommandInterface{
	
	/**
	 * The list of turtles is set to temporarily active, and the tree is traversed to execute
	 * the commands specifically for those turtles. The original list of active turtles is later
	 * reverted back to.
	 */
	
	@Override
	public double Execute(Model model) throws VariableNotFoundException, CommandException {
		model.getTurtleMaster().setTempActiveTurtles(getMyParamValues());
		ExpressionTreeNode secondChild = getIterator().next();
		getTree().traverseKid(secondChild,model);
		return secondChild.getOxygen().getReturnValue();
	}
}
