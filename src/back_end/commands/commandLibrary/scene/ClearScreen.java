package back_end.commands.commandLibrary.scene;

import back_end.model.scene.Model;

import back_end.commands.commandLibrary.SupplierCommand;
import back_end.interfaces.CommandInterface;

public class ClearScreen extends SupplierCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return  model.operateOnTurtle(turtle -> {
			double result = turtle.setPosition(model.getHome());
			turtle.dontDrawLine(); // FIXME lines are not erased 
			return result;
		});
	}
	
	
}
