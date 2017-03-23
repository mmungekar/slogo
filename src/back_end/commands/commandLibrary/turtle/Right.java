package back_end.commands.commandLibrary.turtle;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Right extends LeftRight implements CommandInterface{

	@Override
	protected Double getDirection() {
		return 1.0;
	}
	
}
