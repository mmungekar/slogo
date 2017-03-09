package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class PenDown extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		model.operateOnTurtle(turtle -> turtle.setPen(true));
		return 1;
	}
	

}