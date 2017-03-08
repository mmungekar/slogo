package back_end.commands.commandLibrary.movement;

import back_end.model.scene.Model;
import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;

public class Home extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {		
		return model.operateOnTurtle(turtle -> turtle.setPosition(model.getHome()));
	}
	

}
