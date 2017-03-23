/**
 * This entire file is part of my code masterpiece
 * 
 * 
 * This file was selected for the masterpiece because it demonstrates the implementation of the MultipleTurtleCommand class.
 * It displays the succinctness of the commands in general, while demonstrating how the Factory design pattern applies
 * to the commands in general.
 */
package back_end.commands.commandLibrary.turtle.multiple;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import back_end.commands.commandLibrary.MultipleTurtleCommand;
import back_end.commands.constant.Constant;
import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class Tell extends MultipleTurtleCommand implements CommandInterface{
	/**
	 * Mina Mungekar
	 * A group of turtles, designated by ID, are set as active
	 */
	@Override
	public double Execute(Model model) {
		model.getTurtleMaster().setActiveTurtles(getMyParamValues());
		return getMyParamValues().get(getMyParamValues().size() - 1);
	}
}
