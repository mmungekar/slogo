package back_end.interfaces;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.model.Model;

public interface CommandInterface<A> {
	void setParameters(A... os) throws NotEnoughParameterException;

	double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException;
}
