package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.SupplierCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class YCoordinate extends SupplierCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.getCoordinate(1);
	}

}
