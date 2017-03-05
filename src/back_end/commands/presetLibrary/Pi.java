package back_end.commands.presetLibrary;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;

public class Pi implements CommandInterface<ExpressionTree> {

	@Override
	public void setParameters(Model model, ExpressionTree... os)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		
		
	}

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		
		return Math.PI;
	}

}
