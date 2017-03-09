package back_end.commands.commandLibrary.movement;

import back_end.model.scene.Model;

import back_end.commands.commandLibrary.NoInputCommand;
import back_end.interfaces.CommandInterface;

public class Home extends NoInputCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		return  model.operateOnTurtle(turtle -> turtle.setPosition(model.getHome()));
	}

}
