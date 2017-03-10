package back_end.commands.commandLibrary.turtle;

import java.util.List;
import java.util.function.BiFunction;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
import javafx.geometry.Point2D;

public class SetTowards extends TwoInputTurtleCommand implements CommandInterface{

	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		return (turtle, inputs) -> {
			Point2D newPosition = new Point2D(inputs.get(0), inputs.get(1));
			return turtle.setAngle(newPosition.subtract(turtle.getCenterPosition()).angle(1, 0));
		};
	}

}
