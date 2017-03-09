package back_end.commands.commandLibrary.scene;

import back_end.model.scene.Model;

import back_end.commands.commandLibrary.NoInputCommand;
import back_end.interfaces.CommandInterface;

public class HideTurtle extends NoInputCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		model.operateOnTurtle(turtle -> turtle.setVisible(false));
		return 0;
	}
}
