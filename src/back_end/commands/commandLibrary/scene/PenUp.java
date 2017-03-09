package back_end.commands.commandLibrary.scene;

import java.util.List;
import back_end.commands.commandLibrary.SupplierCommand;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class PenUp extends SupplierCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		return model.operateOnTurtle(turtle -> {
			turtle.setPen(false);
			return 0.0;
		});
	}
}
