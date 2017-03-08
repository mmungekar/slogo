package back_end.commands.commandLibrary.scene;

import back_end.model.scene.Model;
import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;

public class HideTurtle extends SimpleParameterCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		model.operateOnTurtle(turtle -> turtle.setVisible(false));
		return 0;
	}
	

}
