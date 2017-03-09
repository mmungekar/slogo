package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.SupplierCommand;
import back_end.model.scene.Model;

import back_end.interfaces.CommandInterface;

public class ShowTurtle extends SupplierCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		return model.operateOnTurtle(turtle -> {
			turtle.setVisible(true);
			return 1.0;
		});
	}

}
