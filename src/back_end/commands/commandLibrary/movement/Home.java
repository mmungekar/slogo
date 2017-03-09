package back_end.commands.commandLibrary.movement;

import back_end.model.scene.Model;

import java.util.List;
import java.util.function.Function;

import back_end.commands.commandLibrary.SupplierCommand;
import back_end.interfaces.CommandInterface;

public class Home extends SupplierCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		return  model.operateOnTurtle(turtle -> turtle.setPosition(model.getHome()));
	}

}
