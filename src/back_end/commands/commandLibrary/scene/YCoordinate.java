package back_end.commands.commandLibrary.scene;

import java.util.List;
import java.util.function.Function;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.commandLibrary.SupplierCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.scene.Model;

public class YCoordinate extends SupplierCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.getCoordinate(1);
	}

}
