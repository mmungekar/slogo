package back_end.commands.commandLibrary.scene;

import java.util.List;
import java.util.stream.Collectors;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class SetPalette extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		List<Integer> parameters = this.getParameters().stream() .filter(elt -> elt != null)
				.map(elt -> elt.intValue()).collect(Collectors.toList());
		model.setColorRGB(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
		return this.getParameters().get(0);
	}

}
