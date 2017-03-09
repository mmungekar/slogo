package back_end.commands.commandLibrary.movement;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
import javafx.geometry.Point2D;

public class Backward extends DisplacementCommand implements CommandInterface{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return displaceTurtle(model, -1);
	}

}
