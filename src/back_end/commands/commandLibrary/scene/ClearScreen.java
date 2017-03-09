package back_end.commands.commandLibrary.scene;

import back_end.model.scene.Model;

import back_end.commands.commandLibrary.NoInputCommand;
import back_end.interfaces.CommandInterface;

public class ClearScreen extends NoInputCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return  model.operateOnTurtle(turtle -> {
			double result = turtle.setPosition(model.getHome());
			turtle.dontDrawLine(); // FIXME lines are not erased 
			return result;
		});
	}
	
	
}
