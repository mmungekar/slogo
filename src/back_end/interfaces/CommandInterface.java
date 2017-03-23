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
