package back_end.commands.commandLibrary.turtle;

import java.util.List;
import java.util.function.BiFunction;

import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
import javafx.geometry.Point2D;
/**
 * By Miguel Anderson
 * Modified by Mina Mungekar
 */

public abstract class ForwardBackward extends OneInputTurtleCommand implements CommandInterface{
	
	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		BiFunction<Turtle, Double, Double> displacementFunction = (turtle, mag) -> moveForward(turtle, getDirection() * mag);
		return (turtle, inputs) -> turtle.setPosition(displacementFunction.apply(turtle, inputs.get(0)));
	}

	protected abstract Double getDirection();
	
	protected double moveForward(Turtle turtle, double mag){
		return mag;
	}

}
