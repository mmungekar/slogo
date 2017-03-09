package back_end.commands.commandLibrary.scene;

import back_end.model.scene.Model;

import java.util.List;
import java.util.function.Function;

import back_end.commands.commandLibrary.SupplierCommand;
import back_end.interfaces.CommandInterface;

public class HideTurtle extends SupplierCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		model.operateOnTurtle(turtle -> turtle.setVisible(false));
		return 0;
	}
}
