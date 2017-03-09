package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class IsShowing extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.operateOnTurtle(turtle -> {
			 return (turtle.isVisible() == true) ? 1.0 : 0.0;
		});
	}

}
