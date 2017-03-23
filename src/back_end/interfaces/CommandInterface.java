/**
 * This entire file is part of my code masterpiece
 * 
 * This is the most general framework used for any of the commands, and is the starting point from which all other abstract classes
 * are built.
 */
package back_end.interfaces;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;
/**
 * By all members
 */
public interface CommandInterface {
	void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException;

	double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException;
}
