package back_end.commands.commandLibrary.turtle;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public class Forward extends ForwardBackward implements CommandInterface{

	@Override
	protected Double getDirection() {
		return 1.0;
	}


}
