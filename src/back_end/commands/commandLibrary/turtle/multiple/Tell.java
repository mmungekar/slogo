package back_end.commands.commandLibrary.turtle.multiple;

import java.util.List;
import java.util.stream.Collectors;

import back_end.commands.commandLibrary.MultipleTurtleCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Tell extends MultipleTurtleCommand implements CommandInterface{
	/**
	 * A group of turtles, designated by ID, are set as active
	 */
	@Override
	public double Execute(Model model) {
		List<Integer> myParamValues = getFirstChild().getChildren().stream() .filter(elt -> elt != null)
				.map(elt -> elt.getOxygen().getReturnValue().intValue()).collect(Collectors.toList());
		model.getTurtleMaster().setActiveTurtles(myParamValues);
		//System.out.println(myParamValues.size());
		return myParamValues.get(myParamValues.size() - 1);
	}
}
