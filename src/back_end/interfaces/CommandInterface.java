package back_end.interfaces;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.model.ExpressionTree;
import back_end.model.Model;

public interface CommandInterface<A>{
	void setParameters(Model model, A...os) throws NotEnoughParameterException, VariableNotFoundException, CommandException;

	double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException;
}
