package back_end.commands.commandLibrary.multipleTurtles;

import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.scene.Model;

public class Ask implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return 0;
		//return model.getSingleActiveTurtle();
	}

	@Override
	public void setParameters(Model model, ExpressionTree tree)
			throws NotEnoughParameterException, VariableNotFoundException, CommandException {
		// TODO Auto-generated method stub
		
	}
}
